package hu.inf.unideb.hu.employee.Repository.DAO;

import hu.inf.unideb.hu.employee.Exception.*;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.DAO.Implementations.SalaryDAOImpl;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.SalaryDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Repository.Entity.EmployeeEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.SalaryEntity;
import hu.inf.unideb.hu.employee.Repository.SalaryRepository;
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
public class SalaryDAOImplTest {

    @Spy
    @InjectMocks
    private SalaryDAOImpl salaryDAO;

    @Mock
    private SalaryRepository salaryRepository;

    @Test
    public void testCreateSalary() throws DuplicateSalaryException {
        salaryDAO.createSalary(getSalary());
        verify(salaryRepository, times(1)).save(any());
    }

    @Test
    public void testCreateSalaryWithExistingSalary() throws DuplicateSalaryException {
        doThrow(DuplicateSalaryException.class).when(salaryDAO).createSalary(any());
        assertThrows(DuplicateSalaryException.class, () -> {
            salaryDAO.createSalary(getSalary());
        });
    }

    @Test
    public void testCreateSalaryWithExistingSalary2() throws DuplicateSalaryException {
        doReturn(true).when(salaryRepository).existsSalaryEntityBySalaryKey(any());
        assertThrows(DuplicateSalaryException.class, () -> {
            salaryDAO.createSalary(getSalary());
        });
    }

    @Test
    public void testReadAll() {
        salaryDAO.readAll();
        verify(salaryRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteSalary() throws UnknownSalaryException {
        doReturn(Optional.of(getSalaryEntity())).when(salaryRepository).findSalaryEntityBySalaryKey(any());
        salaryDAO.deleteSalary(getSalary().getSalaryKey());
        verify(salaryRepository, times(1)).findSalaryEntityBySalaryKey(getSalary().getSalaryKey());
    }

    @Test
    public void testDeleteSalaryWithUnknownSalary() throws UnknownSalaryException {
        doReturn(Optional.empty()).when(salaryRepository).findSalaryEntityBySalaryKey(any());
        assertThrows(UnknownSalaryException.class, () -> salaryDAO.deleteSalary(getSalary().getSalaryKey()));
        verify(salaryRepository, times(1)).findSalaryEntityBySalaryKey(any());
    }

    @Test
    public void testUpdateSalary() throws UnknownSalaryException, DuplicateSalaryException {
        Salary salary1 = getSalary();
        doReturn(Optional.of(salary1)).when(salaryRepository).findSalaryEntityBySalaryKey(any());
        salaryDAO.updateSalary(salary1, salary1);
        verify(salaryRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateSalaryWithUnknownSalary() throws UnknownSalaryException, DuplicateSalaryException {
        Salary salary1 = getSalary();
        Salary salary2 = getSalary2();
        assertThrows(UnknownSalaryException.class, () -> {
            salaryDAO.updateSalary(salary1, salary2);
        });
    }

    @Test
    public void testGetSalaryByEmpNo() throws UnknownSalaryException, UnknownEmployeeException {
        when(salaryRepository.findSalaryEntitiesByEmpNo(anyInt())).thenReturn(List.of(getSalaryEntity()));
        Collection<Salary> salaries = salaryDAO.getSalariesByEmployee(getSalary().getSalaryKey().getEmpNo());
        assertEquals(List.of(getSalary()), salaries);
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
                .toDate(Date.from(Instant.EPOCH))
                .build();
    }

    private Salary getSalary2() {
        return Salary.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(20000)
                                .fromDate(Date.from(Instant.EPOCH))
                                .build()
                )
                .salary(300)
                .toDate(Date.from(Instant.EPOCH))
                .build();
    }

    private SalaryEntity getSalaryEntity() {
        return SalaryEntity.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(10000)
                                .fromDate(Date.from(Instant.EPOCH))
                                .build()
                )
                .salary(200)
                .toDate(Date.from(Instant.EPOCH))
                .build();
    }

}
