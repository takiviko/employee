package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.SalaryDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Repository.Entity.SalaryEntity;
import hu.inf.unideb.hu.employee.Repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
public class SalaryDAOImpl implements SalaryDAO {

    private final SalaryRepository salaryRepository;

    @Override
    public void createSalary(Salary salary) throws DuplicateSalaryException {
        if(salaryRepository.existsSalaryEntityBySalaryKey(salary.getSalaryKey())) {
            throw new DuplicateSalaryException(
                    "Salary for employee " + salary.getSalaryKey().getEmpNo()
                    + " with fromDate" + salary.getSalaryKey().getFromDate()
                    + " already exists");
        }

        SalaryEntity salaryEntity = convertSalaryToEntity(salary);

        log.info(
                "Salary of " + salary.getSalary()
                + " for employee " + salary.getSalaryKey().getEmpNo()
                + " created"
        );

        try {
            salaryRepository.save(salaryEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException, DuplicateSalaryException {
        Optional<SalaryEntity> oldSalaryEntity = salaryRepository.findSalaryEntityBySalaryKey(oldSalary.getSalaryKey());
        if(oldSalaryEntity.isEmpty()) {
            throw new UnknownSalaryException(("Salary of employee " + oldSalary.getSalaryKey().getEmpNo() + " with fromDate " + oldSalary.getSalaryKey().getFromDate() + " not found" ));
        }

        SalaryEntity salaryEntity = convertSalaryToEntity(newSalary);
        salaryEntity.setSalaryKey(oldSalary.getSalaryKey());

        log.info("Old salary: " + oldSalary.toString());
        log.info("New salary: " + newSalary.toString());

        log.info("Salary of employee " + oldSalary.getSalaryKey().getEmpNo() + " with fromDate " + oldSalary.getSalaryKey().getFromDate() + " updated");

        try {
            salaryRepository.save(salaryEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteSalary(SalaryKey salaryKey) throws UnknownSalaryException {
        Optional<SalaryEntity> salaryEntity = salaryRepository.findSalaryEntityBySalaryKey(salaryKey);
        if(salaryEntity.isEmpty()) {
            throw new UnknownSalaryException("Salary of employee " + salaryKey.getEmpNo() + " at date " + salaryKey.getFromDate() + " not found");
        }

        log.info("Salary of employee " + salaryKey.getEmpNo() + " at date " + salaryKey.getFromDate() + " deleted");
        try {
            salaryRepository.delete(salaryEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Collection<Salary> readAll() {
        return StreamSupport.stream(salaryRepository.findAll().spliterator(), true)
                .map(this::convertEntityToSalary)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Salary> getSalariesByEmployee(int empNo) throws UnknownEmployeeException {
        return StreamSupport.stream(salaryRepository.findSalaryEntitiesByEmpNo(empNo).spliterator(), true)
                .map(this::convertEntityToSalary)
                .collect(Collectors.toList());
    }

    private SalaryEntity convertSalaryToEntity(Salary salary) {
        return SalaryEntity.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(salary.getSalaryKey().getEmpNo())
                                .fromDate(salary.getSalaryKey().getFromDate())
                                .build()
                )
                .salary(salary.getSalary())
                .toDate(salary.getToDate())
                .build();
    }

    private Salary convertEntityToSalary(SalaryEntity salaryEntity) {
        return Salary.builder()
                .salaryKey(
                        SalaryKey.builder()
                                .empNo(salaryEntity.getSalaryKey().getEmpNo())
                                .fromDate(salaryEntity.getSalaryKey().getFromDate())
                                .build()
                )
                .salary(salaryEntity.getSalary())
                .toDate(salaryEntity.getToDate())
                .build();
    }
}
