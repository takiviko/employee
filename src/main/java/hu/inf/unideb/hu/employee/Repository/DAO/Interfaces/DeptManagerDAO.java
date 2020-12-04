package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;

import java.util.Collection;

public interface DeptManagerDAO {

    void addDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException;

    void deleteDeptManager(int emp_no, String dept_no) throws UnknownDeptManagerException;

    void updateDeptManager(DeptManager deptManager) throws UnknownDeptManagerException;

    Collection<DeptManager> getAllManagers();

}
