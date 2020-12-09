package hu.inf.unideb.hu.employee.Service;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.EmployeeDAO;
import hu.inf.unideb.hu.employee.Service.Implementations.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDAO employeeDAO;

    private final Employee employee1 = getEmployee();
    private final Employee employee2 = getEmployee2();


    @Test
    public void testReadAllEmployees() {
        when(employeeDAO.readAll()).thenReturn(getEmployees());
        Collection<Employee> actual = employeeService.getAllEmployees();
        assertThat(getEmployees(), is(actual));
    }

    @Test
    public void testCreateEmployee() throws DuplicateEmployeeException {
        employeeService.createEmployee(employee1);
        verify(employeeDAO, times(1)).createEmployee(employee1);
    }

    @Test
    public void testCreateEmployeeWithDuplicateEmployee() throws DuplicateEmployeeException {
        doThrow(DuplicateEmployeeException.class).when(employeeDAO).createEmployee(any());
        assertThrows(DuplicateEmployeeException.class, () -> {
           employeeService.createEmployee(employee1);
        });
    }

    @Test
    public void testDeleteEmployee() throws UnknownEmployeeException {
        employeeService.deleteEmployee(employee1.getEmpNo());
        verify(employeeDAO, times(1)).deleteEmployee(employee1.getEmpNo());
    }

    @Test
    public void testDeleteEmployeeWithUnknownEmployee() throws UnknownEmployeeException {
        doThrow(UnknownEmployeeException.class).when(employeeDAO).deleteEmployee(anyInt());
        assertThrows(UnknownEmployeeException.class, () -> {
            employeeService.deleteEmployee(employee1.getEmpNo());
        });
    }

    @Test
    public void testUpdateEmployee() throws UnknownEmployeeException, DuplicateEmployeeException {
        employeeService.updateEmployee(employee1, employee2);
        verify(employeeDAO, times(1)).updateEmployee(employee1, employee2);
    }

    @Test
    public void testUpdateEmployeeWithUnknownEmployee() throws UnknownEmployeeException, DuplicateEmployeeException {
        doThrow(UnknownEmployeeException.class).when(employeeDAO).updateEmployee(any(), any());
        assertThrows(UnknownEmployeeException.class, () -> {
            employeeService.updateEmployee(employee1, employee2);
        });
    }

    @Test
    public void testGetEmployeeById() throws UnknownEmployeeException {
        when(employeeDAO.getEmployeeById(anyInt())).thenReturn(employee1);
        Employee employee = employeeService.getEmployeeById(10001);
        assertThat(employee1, is(employee));
    }

    @Test
    public void testGetEmployeeByIdWithUnknownDepartment() throws UnknownEmployeeException {
        doThrow(UnknownEmployeeException.class).when(employeeDAO).getEmployeeById(anyInt());
        assertThrows(UnknownEmployeeException.class, () -> {
            employeeService.getEmployeeById(employee1.getEmpNo());
        });
    }

    @Test
    public void testGetEmployeesByGender() throws UnknownEmployeeException {
        when(employeeDAO.getEmployeesByGender(anyString())).thenReturn(List.of(employee1));
        Collection<Employee> employees = employeeService.getEmployeesByGender("M");
        assertThat(List.of(employee1), is(employees));
    }


    private Employee getEmployee() {
        return Employee.builder()
                .empNo(10000)
                .hireDate(Date.from(Instant.now()))
                .birthDate(Date.from(Instant.EPOCH))
                .firstName("John")
                .lastName("Doe")
                .gender("M")
                .build();
    }

    private Employee getEmployee2() {
        return Employee.builder()
                .empNo(10001)
                .hireDate(Date.from(Instant.now()))
                .birthDate(Date.from(Instant.EPOCH))
                .firstName("Johnny")
                .lastName("Doe")
                .gender("M")
                .build();
    }

    private Collection<Employee> getEmployees() {
        return List.of(this.employee1, this.employee2);
    }



}
