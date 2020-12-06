package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.SalaryDAO;
import hu.inf.unideb.hu.employee.Repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class SalaryDAOImpl implements SalaryDAO {

    private final SalaryRepository salaryRepository;

    @Override
    public void addSalary(Salary salary) throws DuplicateSalaryException {

    }

    @Override
    public void updateSalary(Salary salary) throws UnknownSalaryException {

    }

    @Override
    public void deleteSalary(Salary salary) throws UnknownSalaryException {

    }

    @Override
    public Collection<Salary> getAllSalaries() {
        return null;
    }

    @Override
    public Collection<Salary> getAllSalariesByEmployee(int emp_id) throws UnknownEmployeeException {
        return null;
    }
}
