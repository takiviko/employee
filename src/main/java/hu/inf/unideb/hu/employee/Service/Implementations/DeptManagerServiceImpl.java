package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptManagerService;

import java.util.Collection;

public class DeptManagerServiceImpl implements DeptManagerService {
    @Override
    public void addDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException {

    }

    @Override
    public void deleteDeptManager(int emp_no, String dept_no) throws UnknownDeptManagerException {

    }

    @Override
    public void updateDeptManager(DeptManager deptManager) throws UnknownDeptManagerException {

    }

    @Override
    public Collection<DeptManager> getAllManagers() {
        return null;
    }
}
