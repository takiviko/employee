package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Model.Enums.Gender;

import java.util.Collection;

public interface EmployeeService {

    void addEmployee(Employee employee) throws DuplicateEmployeeException;

    void deleteEmployee(Employee employee) throws UnknownEmployeeException;

    void updateEmployee(Employee oldEmployee, Employee newEmployee) throws UnknownEmployeeException;

    Collection<Employee> getAllEmployees();

    Employee getEmployeeById(int emp_id) throws UnknownEmployeeException;

    Collection<Employee> getEmployeesByGender(Gender gender) throws UnknownEmployeeException;

}
