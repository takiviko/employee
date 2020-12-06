package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Model.Enums.Gender;
import hu.inf.unideb.hu.employee.Service.Interfaces.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void addEmployee(Employee employee) throws DuplicateEmployeeException {

    }

    @Override
    public void deleteEmployee(Employee employee) throws UnknownEmployeeException {

    }

    @Override
    public void updateEmployee(Employee oldEmployee, Employee newEmployee) throws UnknownEmployeeException {

    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployeeById(int emp_id) throws UnknownEmployeeException {
        return null;
    }

    @Override
    public Collection<Employee> getEmployeesByGender(Gender gender) throws UnknownEmployeeException {
        return null;
    }
}
