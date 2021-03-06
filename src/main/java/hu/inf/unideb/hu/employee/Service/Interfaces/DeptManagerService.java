package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;

import java.util.Collection;

public interface DeptManagerService {

    void addDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException;

    void deleteDeptManager(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException;

    void updateDeptManager(DeptManager oldDeptManager, DeptManager newDeptManager) throws UnknownDeptManagerException, DuplicateDeptManagerException;

    DeptManager getDeptManagerByKey(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException;

    Collection<DeptManager> getAllManagers();
}
