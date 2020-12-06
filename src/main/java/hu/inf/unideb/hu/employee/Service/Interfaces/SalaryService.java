package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;

import java.util.Collection;

public interface SalaryService {

    void addSalary(Salary salary) throws DuplicateSalaryException;

    void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException;

    void deleteSalary(Salary salary) throws UnknownSalaryException;

    Collection<Salary> getAllSalaries();

    Collection<Salary> getAllSalariesByEmployee(int emp_id) throws UnknownEmployeeException;

}
