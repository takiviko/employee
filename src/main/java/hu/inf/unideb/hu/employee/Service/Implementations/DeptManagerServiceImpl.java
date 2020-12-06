package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptManagerDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class DeptManagerServiceImpl implements DeptManagerService {

    private final DeptManagerDAO deptManagerDAO;

    @Override
    public void addDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException {
        deptManagerDAO.createDeptManager(deptManager);
    }

    @Override
    public void deleteDeptManager(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException {
        deptManagerDAO.deleteDeptManager(deptManagerKey);
    }

    @Override
    public void updateDeptManager(DeptManager oldDeptManager, DeptManager newDeptManager) throws UnknownDeptManagerException, DuplicateDeptManagerException {
        deptManagerDAO.updateDeptManager(oldDeptManager, newDeptManager);
    }

    @Override
    public DeptManager getDeptManagerByKey(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException {
        return deptManagerDAO.getDeptManagerByKey(deptManagerKey);
    }

    @Override
    public Collection<DeptManager> getAllManagers() {
        return deptManagerDAO.getAllManagers();
    }
}
