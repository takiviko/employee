package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;
import hu.inf.unideb.hu.employee.Service.Interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    @Override
    public Collection<Department> getAllDepartments() {
        return departmentDAO.readAll();
    }

    @Override
    public void addDepartment(Department department) throws DuplicateDepartmentException, UnknownDepartmentException {
        departmentDAO.deleteDepartment(department.getDeptName());
    }

    @Override
    public void deleteDepartment(String deptName) throws UnknownDepartmentException {
        departmentDAO.deleteDepartment(deptName);
    }

    @Override
    public void createDepartment(Department department) throws DuplicateDepartmentException {
        departmentDAO.createDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) throws UnknownDepartmentException {

    }

    @Override
    public Department getDepartmentByDeptName(String deptName) throws UnknownDepartmentException {
        return departmentDAO.getDepartmentByName(deptName);
    }
}
