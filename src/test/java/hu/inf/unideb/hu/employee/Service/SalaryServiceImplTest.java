package hu.inf.unideb.hu.employee.Service;

import hu.inf.unideb.hu.employee.Exception.*;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.SalaryDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Service.Implementations.SalaryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class SalaryServiceImplTest {

    @InjectMocks
    private SalaryServiceImpl salaryService;

    @Mock
    private SalaryDAO salaryDAO;

    private final Salary salary1 = getSalary();
    private final Salary salary2 = getSalary2();

    @Test
    public void testReadAllSalaries() {
        when(salaryDAO.readAll()).thenReturn(getSalaries());
        Collection<Salary> actual = salaryService.readAllSalaries();
        assertThat(getSalaries(), is(actual));
    }

    @Test
    public void testCreateSalary() throws DuplicateSalaryException {
        salaryService.createSalary(salary1);
        verify(salaryDAO, times(1)).createSalary(salary1);
    }

    @Test
    public void testCreateSalaryWithDuplicateKey() throws DuplicateSalaryException {
        doThrow(DuplicateSalaryException.class).when(salaryDAO).createSalary(any());
        assertThrows(DuplicateSalaryException.class, () -> {
            salaryService.createSalary(salary1);
        });
    }

    @Test
    public void testDeleteSalary() throws UnknownSalaryException {
        salaryService.deleteSalary(salary1.getSalaryKey());
        verify(salaryDAO, times(1)).deleteSalary(salary1.getSalaryKey());
    }

    @Test
    public void testDeleteSalaryWithUnknownSalary() throws UnknownSalaryException {
        doThrow(UnknownSalaryException.class).when(salaryDAO).deleteSalary(any());
        assertThrows(UnknownSalaryException.class, () -> {
            salaryService.deleteSalary(salary1.getSalaryKey());
        });
    }

    @Test
    public void testUpdateDepartment() throws UnknownSalaryException, DuplicateSalaryException {
        salaryService.updateSalary(salary1, salary2);
        verify(salaryDAO, times(1)).updateSalary(salary1, salary2);
    }

    @Test
    public void testUpdateSalaryWithUnknownSalary() throws UnknownSalaryException, DuplicateSalaryException {
        doThrow(UnknownSalaryException.class).when(salaryDAO).updateSalary(any(), any());
        assertThrows(UnknownSalaryException.class, () -> {
            salaryService.updateSalary(salary1, salary2);
        });
    }

    @Test
    public void testGetSalariesByEmpNo() throws UnknownEmployeeException {
        when(salaryDAO.getSalariesByEmployee(anyInt())).thenReturn(List.of(salary1));
        Collection<Salary> salaries = salaryService.getSalariesByEmployee(10000);
        assertThat(List.of(salary1), is(salaries));
    }

    private Salary getSalary() {
        return Salary.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(10000)
                                .fromDate(Date.from(Instant.EPOCH))
                                .build()
                )
                .salary(200)
                .toDate(Date.from(Instant.now()))
                .build();
    }

    private Salary getSalary2() {
        return Salary.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(10001)
                                .fromDate(Date.from(Instant.now()))
                                .build()
                )
                .salary(300)
                .toDate(Date.from(Instant.now()))
                .build();
    }

    private Collection<Salary> getSalaries() {
        return List.of(salary1, salary2);
    }

}
