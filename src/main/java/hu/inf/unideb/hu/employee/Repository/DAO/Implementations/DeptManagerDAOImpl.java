package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptManagerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class DeptManagerDAOImpl implements DeptManagerDAO {


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
