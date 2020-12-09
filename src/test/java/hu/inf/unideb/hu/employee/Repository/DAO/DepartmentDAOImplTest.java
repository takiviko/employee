package hu.inf.unideb.hu.employee.Repository.DAO;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Repository.DAO.Implementations.DepartmentDAOImpl;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;
import hu.inf.unideb.hu.employee.Repository.DepartmentRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.DepartmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentDAOImplTest {

    @Spy
    @InjectMocks
    private DepartmentDAOImpl departmentDAO;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void testCreateDepartment() throws DuplicateDepartmentException {
        departmentDAO.createDepartment(getDepartment());
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    public void testCreateDepartmentWithExistingDepartment() throws DuplicateDepartmentException {
        doThrow(DuplicateDepartmentException.class).when(departmentDAO).createDepartment(any());
        assertThrows(DuplicateDepartmentException.class, () -> {
           departmentDAO.createDepartment(getDepartment());
        });
    }

    @Test
    public void testCreateDepartmentWithExistingDepartment2() throws DuplicateDepartmentException {
        doReturn(true).when(departmentRepository).existsDepartmentEntityByDeptName(anyString());
        assertThrows(DuplicateDepartmentException.class, () -> {
           departmentDAO.createDepartment(getDepartment());
        });
    }

    @Test
    public void testReadAll() {
        departmentDAO.readAll();
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteDepartment() throws UnknownDepartmentException {
        doReturn(Optional.of(getDepartmentEntity())).when(departmentRepository).findByDeptName(anyString());
        departmentDAO.deleteDepartment(getDepartment().getDeptName());
        verify(departmentRepository, times(1)).findByDeptName(getDepartment().getDeptName());
    }

    @Test
    public void testDeleteDepartmentWithUnknownDepartment() throws UnknownDepartmentException {
        doReturn(Optional.empty()).when(departmentRepository).findByDeptName(anyString());
        assertThrows(UnknownDepartmentException.class, () -> departmentDAO.deleteDepartment(getDepartment().getDeptName()));
        verify(departmentRepository, times(1)).findByDeptName(any());
    }

    @Test
    public void testUpdateDepartment() throws UnknownDepartmentException, DuplicateDepartmentException {
        Department department1 = getDepartment();
        Department department2 = getDepartment2();
        doReturn(Optional.of(department1)).when(departmentRepository).findByDeptName(anyString());
        departmentDAO.updateDepartment(department1, department2);
        verify(departmentRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateDepartmentWithUnknownDepartment() throws UnknownDepartmentException, DuplicateDepartmentException {
        Department department1 = getDepartment();
        Department department2 = getDepartment2();
        assertThrows(UnknownDepartmentException.class, () -> {
            departmentDAO.updateDepartment(department1, department2);
        });
    }

    @Test
    public void testGetDepartmentByName() throws UnknownDepartmentException {
        when(departmentRepository.findByDeptName(anyString())).thenReturn(Optional.of(getDepartmentEntity()));
        Department department = departmentDAO.getDepartmentByName(getDepartment().getDeptName());
        assertEquals(getDepartment(), department);
    }

    @Test
    public void testGetDepartmentByNameWithUnknownDeptName() throws UnknownDepartmentException {
        assertThrows(UnknownDepartmentException.class, () -> {
            departmentDAO.getDepartmentByName(anyString());
        });
    }



    private Department getDepartment() {
        return Department.builder()
                .deptName("HR")
                .deptNo("d001")
                .build();
    }

    private Department getDepartment2() {
        return Department.builder()
                .deptName("HR")
                .deptNo("d002")
                .build();
    }

    private DepartmentEntity getDepartmentEntity() {
        return DepartmentEntity.builder()
                .deptName("HR")
                .deptNo("d001")
                .build();
    }

}
