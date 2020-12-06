package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;

import java.util.Collection;

public interface DeptManagerDAO {

    void createDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException;

    void deleteDeptManager(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException;

    void updateDeptManager(DeptManager oldDeptManager, DeptManager newDeptManager) throws UnknownDeptManagerException, DuplicateDeptManagerException;

    Collection<DeptManager> getAllManagers();

    public DeptManager getDeptManagerByKey(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException;

}
