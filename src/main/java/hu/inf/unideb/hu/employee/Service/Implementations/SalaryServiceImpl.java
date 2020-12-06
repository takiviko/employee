package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Service.Interfaces.SalaryService;

import java.util.Collection;

public class SalaryServiceImpl implements SalaryService {

    @Override
    public void addSalary(Salary salary) throws DuplicateSalaryException {

    }

    @Override
    public void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException {

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
