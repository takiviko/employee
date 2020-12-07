package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;

import java.util.Collection;

public interface EmployeeDAO {

    void createEmployee(Employee employee) throws DuplicateEmployeeException;

    void deleteEmployee(int empNo) throws UnknownEmployeeException;

    void updateEmployee(Employee oldEmployee, Employee newEmployee) throws UnknownEmployeeException, DuplicateEmployeeException;

    Collection<Employee> getAllEmployees();

    Employee getEmployeeById(int emp_id) throws UnknownEmployeeException;

    Collection<Employee> getEmployeesByGender(String gender) throws UnknownEmployeeException;

}
