package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.SalaryDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Slf4j
public class SalaryServiceImpl implements SalaryService {

    private final SalaryDAO salaryDAO;

    @Override
    public void createSalary(Salary salary) throws DuplicateSalaryException {
        salaryDAO.createSalary(salary);
    }

    @Override
    public void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException, DuplicateSalaryException {
        salaryDAO.updateSalary(oldSalary, newSalary);
    }

    @Override
    public void deleteSalary(SalaryKey salaryKey) throws UnknownSalaryException {
        salaryDAO.deleteSalary(salaryKey);
    }

    @Override
    public Collection<Salary> readAllSalaries() {
        return salaryDAO.readAll();
    }

    @Override
    public Collection<Salary> getSalariesByEmployee(int empNo) throws UnknownEmployeeException {
        return salaryDAO.getSalariesByEmployee(empNo);
    }
}
