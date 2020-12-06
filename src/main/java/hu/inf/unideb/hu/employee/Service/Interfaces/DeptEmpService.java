package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptEmpDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;

import java.util.Collection;

public interface DeptEmpService {

    void addDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException;

    void deleteDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException;

    void updateDeptEmp(DeptEmp oldDeptEmp, DeptEmp newDeptEmp) throws UnknownDeptEmpException, UnknownEmployeeException, DuplicateDeptEmpException;

    DeptEmp getDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException;

    Collection<DeptEmp> getAllDeptEmps();
}
