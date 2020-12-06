package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;

import java.util.Collection;

public interface DeptEmpDAO {

    void createDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException;

    void deleteDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException;

    void updateDeptEmp(DeptEmp deptEmp) throws UnknownDeptEmpException, UnknownEmployeeException;

    DeptEmp getDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException;

    Collection<DeptEmp> getAllDeptEmps();

}
