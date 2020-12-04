package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Model.Enums.Gender;

import java.util.Collection;

public interface EmployeeDAO {

    void addEmployee(Employee employee) throws DuplicateEmployeeException;

    void deleteEmployee(Employee employee) throws UnknownEmployeeException;

    void updateEmployee(Employee employee) throws UnknownEmployeeException;

    Collection<Employee> getAllEmployees();

    Employee getEmployeeById(int emp_id) throws UnknownEmployeeException;

    Collection<Employee> getEmployeesByGender(Gender gender) throws UnknownEmployeeException;

}
