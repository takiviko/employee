package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import java.util.Collection;

public interface DepartmentDAO {

    void deleteDepartment(String dept_name) throws UnknownDepartmentException;

    void updateDepartment(Department oldDepartment, Department newDepartment) throws UnknownDepartmentException, DuplicateDepartmentException;

    void createDepartment(Department department) throws DuplicateDepartmentException;

    Department getDepartmentByName(String dept_name) throws UnknownDepartmentException;

    Collection<Department> readAll();
}
