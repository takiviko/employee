package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptEmpDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class DeptEmpDAOImpl implements DeptEmpDAO {


    @Override
    public void addDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException {

    }

    @Override
    public void deleteDeptEmp(int emp_no, String dept_no) throws UnknownDeptEmpException {

    }

    @Override
    public void updateDeptEmp(DeptEmp deptEmp) throws UnknownDeptEmpException, UnknownEmployeeException {

    }

    @Override
    public DeptEmp getDeptEmp(int emp_no, String dept_no) throws UnknownDeptEmpException {
        return null;
    }

    @Override
    public Collection<DeptEmp> getAllDeptEmps() {
        return null;
    }
}
