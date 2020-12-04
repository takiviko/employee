package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptEmpService;

import java.util.Collection;

public class DeptEmpServiceImpl implements DeptEmpService {

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
