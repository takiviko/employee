package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;

import java.util.Collection;

public interface DepartmentService {

    Collection<Department> getAllDepartments();

    void addDepartment(Department department) throws DuplicateDepartmentException, UnknownDepartmentException;

    void deleteDepartment(String dept_name) throws UnknownDepartmentException;

    void createDepartment(Department department) throws DuplicateDepartmentException;

    void updateDepartment(Department department) throws UnknownDepartmentException;

    Department getDepartmentByDeptName(String dept_name) throws UnknownDepartmentException;

}
