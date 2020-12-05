package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;

import java.util.Collection;

public interface DeptEmpDAO {

    void createDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException;

    void deleteDeptEmp(int emp_no, String dept_no) throws UnknownDeptEmpException;

    void updateDeptEmp(DeptEmp deptEmp) throws UnknownDeptEmpException, UnknownEmployeeException;

    DeptEmp getDeptEmp(int emp_no, String dept_no) throws UnknownDeptEmpException;

    Collection<DeptEmp> getAllDeptEmps();

}
