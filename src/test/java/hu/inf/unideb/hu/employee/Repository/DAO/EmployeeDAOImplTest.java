package hu.inf.unideb.hu.employee.Repository.DAO;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Repository.DAO.Implementations.EmployeeDAOImpl;
import hu.inf.unideb.hu.employee.Repository.DepartmentRepository;
import hu.inf.unideb.hu.employee.Repository.EmployeeRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.DepartmentEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeDAOImplTest {

    @Spy
    @InjectMocks
    private EmployeeDAOImpl employeeDAO;

    @Mock
    private EmployeeRepository employeeRepository;

    Employee employee1 = getEmployee();
    Employee employee2 = getEmployee2();

    EmployeeEntity employeeEntity = getEmployeeEntity();

    @Test
    public void testCreateEmployee() throws DuplicateEmployeeException {
        employeeDAO.createEmployee(getEmployee());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    public void testCreateEmployeeWithExistingEmployee() throws DuplicateEmployeeException {
        doThrow(DuplicateEmployeeException.class).when(employeeDAO).createEmployee(any());
        assertThrows(DuplicateEmployeeException.class, () -> {
            employeeDAO.createEmployee(getEmployee());
        });
    }

    @Test
    public void testCreateEmployeeWithExistingEmployee2() throws DuplicateEmployeeException {
        doReturn(true).when(employeeRepository).existsEmployeeEntityByEmpNo(anyInt());
        assertThrows(DuplicateEmployeeException.class, () -> {
            employeeDAO.createEmployee(getEmployee());
        });
    }

    @Test
    public void testReadAll() {
        employeeDAO.readAll();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteEmployee() throws UnknownEmployeeException {
        doReturn(Optional.of(getEmployeeEntity())).when(employeeRepository).findEmployeeEntityByEmpNo(anyInt());
        employeeDAO.deleteEmployee(getEmployee().getEmpNo());
        verify(employeeRepository, times(1)).findEmployeeEntityByEmpNo(getEmployee().getEmpNo());
    }

    @Test
    public void testDeleteEmployeeWithUnknownEmployee() throws UnknownEmployeeException {
        doReturn(Optional.empty()).when(employeeRepository).findEmployeeEntityByEmpNo(anyInt());
        assertThrows(UnknownEmployeeException.class, () -> employeeDAO.deleteEmployee(getEmployee().getEmpNo()));
        verify(employeeRepository, times(1)).findEmployeeEntityByEmpNo(anyInt());
    }

    @Test
    public void testDeleteEmployeeWithDuplicateEmployee() throws UnknownEmployeeException {
        doReturn(Optional.empty()).when(employeeRepository).findEmployeeEntityByEmpNo(anyInt());
        assertThrows(UnknownEmployeeException.class, () -> employeeDAO.deleteEmployee(getEmployee().getEmpNo()));
        verify(employeeRepository, times(1)).findEmployeeEntityByEmpNo(anyInt());
    }

    @Test
    public void testUpdateDepartment() throws UnknownEmployeeException, DuplicateEmployeeException {
        Employee employee1 = getEmployee();
        Employee employee2 = getEmployee2();
        doReturn(Optional.of(employee1)).when(employeeRepository).findEmployeeEntityByEmpNo(anyInt());
        employeeDAO.updateEmployee(employee1, employee2);
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateEmployeeWithUnknownEmployee() throws DuplicateEmployeeException, UnknownEmployeeException {
        Employee employee1 = getEmployee();
        Employee employee2 = getEmployee2();
        assertThrows(UnknownEmployeeException.class, () -> {
            employeeDAO.updateEmployee(employee1, employee2);
        });
    }

    @Test
    public void testGetEmployeesByGender() {
        employeeDAO.getEmployeesByGender(employee1.getGender());
        verify(employeeRepository, times(1)).findEmployeeEntitiesByGender(employee1.getGender());
    }

    @Test
    public void getEmployeeByIdTest() throws UnknownEmployeeException {
        when(employeeRepository.findEmployeeEntityByEmpNo(anyInt())).thenReturn(Optional.of(employeeEntity));
        Employee employee = employeeDAO.getEmployeeById(employee1.getEmpNo());
        assertEquals(employee1, employee);
    }

    @Test
    public void getEmployeeByIdWithUnknownEmployeeTest() throws UnknownEmployeeException {
        assertThrows(UnknownEmployeeException.class, () -> {
           employeeDAO.getEmployeeById(anyInt());
        });
    }


    private Employee getEmployee() {
        return Employee.builder()
                .empNo(10000)
                .hireDate(Date.from(Instant.EPOCH))
                .birthDate(Date.from(Instant.EPOCH))
                .firstName("John")
                .lastName("Doe")
                .gender("M")
                .build();
    }

    private Employee getEmployee2() {
        return Employee.builder()
                .empNo(10001)
                .hireDate(Date.from(Instant.EPOCH))
                .birthDate(Date.from(Instant.EPOCH))
                .firstName("Jane")
                .lastName("Doe")
                .gender("F")
                .build();
    }

    private EmployeeEntity getEmployeeEntity() {
        return EmployeeEntity.builder()
                .empNo(10000)
                .hireDate(Date.from(Instant.EPOCH))
                .birthDate(Date.from(Instant.EPOCH))
                .firstName("John")
                .lastName("Doe")
                .gender("M")
                .build();
    }

}
