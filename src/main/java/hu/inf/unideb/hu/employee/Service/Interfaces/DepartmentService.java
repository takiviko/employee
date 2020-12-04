package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;

import java.util.Collection;

public interface DepartmentService {

    Collection<Department> getAllDepartments();

    void addDepartment(Department department) throws DuplicateDepartmentException, UnknownDepartmentException;

    void deleteDepartment(String dept_name) throws UnknownDepartmentException;

    void updateDepartment(Department department) throws UnknownDepartmentException;

    Department getDepartmentByDeptName(String dept_name) throws UnknownDepartmentException;

}
