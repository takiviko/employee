package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;

import java.util.Collection;
import java.util.Date;

public interface SalaryService {

    void createSalary(Salary salary) throws DuplicateSalaryException;

    void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException, DuplicateSalaryException;

    void deleteSalary(SalaryKey salaryKey) throws UnknownSalaryException;

    Collection<Salary> readAllSalaries();

    Collection<Salary> getSalariesByEmployee(int emp_id) throws UnknownEmployeeException;

}
