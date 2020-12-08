package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;

import java.util.Date;
import java.util.Collection;

public interface SalaryDAO {
    void createSalary(Salary salary) throws DuplicateSalaryException;

    void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException, DuplicateSalaryException;

    void deleteSalary(SalaryKey salaryKey) throws UnknownSalaryException;

    Collection<Salary> readAllSalaries();

    Collection<Salary> getSalariesByEmployee(int empNo) throws UnknownEmployeeException;

}
