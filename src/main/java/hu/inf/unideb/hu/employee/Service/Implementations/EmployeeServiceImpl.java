package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.EmployeeDAO;
import hu.inf.unideb.hu.employee.Service.Interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public void createEmployee(Employee employee) throws DuplicateEmployeeException {
        employeeDAO.createEmployee(employee);
    }

    @Override
    public void deleteEmployee(int empNo) throws UnknownEmployeeException {
        employeeDAO.deleteEmployee(empNo);
    }

    @Override
    public void updateEmployee(Employee oldEmployee, Employee newEmployee) throws UnknownEmployeeException, DuplicateEmployeeException {
        employeeDAO.updateEmployee(oldEmployee, newEmployee);
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int empNo) throws UnknownEmployeeException {
        return employeeDAO.getEmployeeById(empNo);
    }

    @Override
    public Collection<Employee> getEmployeesByGender(String gender) throws UnknownEmployeeException {
        return employeeDAO.getEmployeesByGender(gender);
    }
}
