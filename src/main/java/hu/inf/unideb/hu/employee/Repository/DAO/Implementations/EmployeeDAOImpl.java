package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateEmployeeException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.EmployeeDAO;
import hu.inf.unideb.hu.employee.Repository.EmployeeRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) throws DuplicateEmployeeException {
        if(employeeRepository.existsEmployeeEntityByEmpNo(employee.getEmpNo())) {
            throw new DuplicateEmployeeException("Employee " + employee.getEmpNo() + " already exists");
        }

        EmployeeEntity employeeEntity = convertEmployeeToEntity(employee);

        log.info("Employee " + employee.getEmpNo() + " created");
        try {
            employeeRepository.save(employeeEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    @Transactional
    public void deleteEmployee(int empNo) throws UnknownEmployeeException {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findEmployeeEntityByEmpNo(empNo);
        if(employeeEntity.isEmpty()) {
            throw new UnknownEmployeeException("Employee " + empNo + " not found");
        }

        log.info("Employee " + empNo + " deleted");
        try {
            employeeRepository.delete(employeeEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee oldEmployee, Employee newEmployee) throws UnknownEmployeeException, DuplicateEmployeeException {
        Optional<EmployeeEntity> oldEmployeeEntity = employeeRepository.findEmployeeEntityByEmpNo(oldEmployee.getEmpNo());
        if(oldEmployeeEntity.isEmpty()) {
            throw new UnknownEmployeeException("Employee " + oldEmployee.getEmpNo() + " not found");
        }
        if(employeeRepository.existsEmployeeEntityByEmpNo(newEmployee.getEmpNo())) {
            throw new DuplicateEmployeeException("Employee " + newEmployee.getEmpNo() + " already exists");
        }

        EmployeeEntity employeeEntity = convertEmployeeToEntity(newEmployee);
        employeeEntity.setEmpNo(oldEmployee.getEmpNo());

        log.info("Employee " + employeeEntity.getEmpNo() + " updated");
        try {
            employeeRepository.save(employeeEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), true)
                .map(this::convertEntityToEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(int empNo) throws UnknownEmployeeException {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findEmployeeEntityByEmpNo(empNo);
        log.info("Fetching employee " + empNo);
        if(employeeEntity.isEmpty()) {
            throw new UnknownEmployeeException("Employee " + employeeEntity.toString() + " not found");
        }
        return convertEntityToEmployee(employeeEntity.get());
    }

    @Override
    public Collection<Employee> getEmployeesByGender(String gender) {
                return employeeRepository.findEmployeeEntityByGender(gender).stream()
                .map(this::convertEntityToEmployee)
                .collect(Collectors.toList());
    }

    protected EmployeeEntity convertEmployeeToEntity(Employee employee) {
        return EmployeeEntity.builder()
                .empNo(employee.getEmpNo())
                .birthDate(employee.getBirthDate())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .hireDate(employee.getHireDate())
                .build();
    }

    protected Employee convertEntityToEmployee(EmployeeEntity employeeEntity) {
        return Employee.builder()
                .empNo(employeeEntity.getEmpNo())
                .birthDate(employeeEntity.getBirthDate())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .gender(employeeEntity.getGender())
                .hireDate(employeeEntity.getHireDate())
                .build();
    }


}
