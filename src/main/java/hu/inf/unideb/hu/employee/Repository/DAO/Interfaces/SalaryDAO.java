package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownSalaryException;
import hu.inf.unideb.hu.employee.Model.Salary;

import java.util.Collection;

public interface SalaryDAO {
    void addSalary(Salary salary) throws DuplicateSalaryException;

    void updateSalary(Salary oldSalary, Salary newSalary) throws UnknownSalaryException;

    void deleteSalary(int empNo) throws UnknownSalaryException;

    Collection<Salary> getAllSalaries();

    Collection<Salary> getAllSalariesByEmployee(int empNo) throws UnknownEmployeeException;

}
