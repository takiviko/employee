package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptManagerDAO;
import hu.inf.unideb.hu.employee.Repository.DeptEmpRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class DeptManagerDAOImpl implements DeptManagerDAO {

    private final DeptEmpRepository deptEmpRepository;

    @Override
    public void addDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException {

    }

    @Override
    public void deleteDeptManager(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException {

    }

    @Override
    public void updateDeptManager(DeptManager deptManager) throws UnknownDeptManagerException {

    }

    @Override
    public Collection<DeptManager> getAllManagers() {
        return null;
    }
}
