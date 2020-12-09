package hu.inf.unideb.hu.employee.Service;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;
import hu.inf.unideb.hu.employee.Service.Implementations.DepartmentServiceImpl;
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

import java.util.Collection;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    DepartmentDAO departmentDAO;

    @Test
    public void testReadAllDepartments() {
        when(departmentDAO.readAll()).thenReturn(getDepartments());
        Collection<Department> actual = departmentService.getAllDepartments();
        assertThat(getDepartments(), is(actual));
    }

    @Test
    public void testCreateDepartment() throws DuplicateDepartmentException {
        Department department = getDepartment();
        departmentService.createDepartment(department);
        verify(departmentDAO, times(1)).createDepartment(department);
    }

    @Test
    public void testCreateDepartmentWithDuplicateDeptNo() throws DuplicateDepartmentException{
        doThrow(DuplicateDepartmentException.class).when(departmentDAO).createDepartment(any());
        assertThrows(DuplicateDepartmentException.class, () -> {
            departmentService.createDepartment(getDepartment());
        });
    }

    @Test
    public void testDeleteDepartment() throws UnknownDepartmentException {
        Department department = getDepartment();
        departmentService.deleteDepartment(department.getDeptName());
        verify(departmentDAO, times(1)).deleteDepartment(department.getDeptName());
    }

    @Test
    public void testDeleteDepartmentWithUnknownDepartment() throws UnknownDepartmentException {
        doThrow(UnknownDepartmentException.class).when(departmentDAO).deleteDepartment(anyString());
        assertThrows(UnknownDepartmentException.class, () -> {
           departmentService.deleteDepartment(getDepartment().getDeptName());
        });
    }

    @Test
    public void testUpdateDepartment() throws UnknownDepartmentException, DuplicateDepartmentException {
        Department department1 = getDepartment();
        Department department2 = getDepartment2();
        departmentService.updateDepartment(department1, department2);
        verify(departmentDAO, times(1)).updateDepartment(department1, department2);
    }

    @Test
    public void testUpdateDepartmentWithUnknownDepartment() throws UnknownDepartmentException, DuplicateDepartmentException {
        Department department1 = getDepartment();
        Department department2 = getDepartment2();
        doThrow(UnknownDepartmentException.class).when(departmentDAO).updateDepartment(any(), any());
        assertThrows(UnknownDepartmentException.class, () -> {
                    departmentService.updateDepartment(department1, department2);
                });
    }

    @Test
    public void testGetDepartmentByDeptName() throws UnknownDepartmentException {
        when(departmentDAO.getDepartmentByName(anyString())).thenReturn(getDepartment());
        Department department = departmentService.getDepartmentByDeptName("d001");
        assertThat(getDepartment(), is(department));
    }

    @Test
    public void testGetDepartmentByDeptNameWithUnknownDepartment() throws UnknownDepartmentException {
        doThrow(UnknownDepartmentException.class).when(departmentDAO).getDepartmentByName(anyString());
        assertThrows(UnknownDepartmentException.class, () -> {
            departmentService.getDepartmentByDeptName(getDepartment().getDeptName());
        });
    }

    private Department getDepartment() {
        return Department.builder()
                .deptNo("d001")
                .deptName("HR")
                .build();
    }

    private Department getDepartment2() {
        return Department.builder()
                .deptNo("d002")
                .deptName("HR")
                .build();
    }

    private Collection<Department> getDepartments() {
        return List.of(
                Department.builder()
                        .deptNo("d001")
                        .deptName("HR")
                        .build(),
                Department.builder()
                        .deptNo("d002")
                        .deptName("Development")
                        .build()
        );
    }

}
