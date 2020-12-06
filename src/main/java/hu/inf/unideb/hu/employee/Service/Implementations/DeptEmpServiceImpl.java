package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Controller.DTO.DeptEmpDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptEmpDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.DeptEmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class DeptEmpServiceImpl implements DeptEmpService {

    private final DeptEmpDAO deptEmpDAO;

    @Override
    public void addDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException {
        deptEmpDAO.createDeptEmp(deptEmp);
    }

    @Override
    public void deleteDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException {
        deptEmpDAO.deleteDeptEmp(deptEmpKey);
    }

    @Override
    public void updateDeptEmp(DeptEmp oldDeptEmp, DeptEmp newDeptEmp) throws UnknownDeptEmpException, UnknownEmployeeException, DuplicateDeptEmpException {
        deptEmpDAO.updateDeptEmp(oldDeptEmp, newDeptEmp);
    }

    @Override
    public DeptEmp getDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException {
        return deptEmpDAO.getDeptEmp(deptEmpKey);
    }

    @Override
    public Collection<DeptEmp> getAllDeptEmps() {
        return deptEmpDAO.getAllDeptEmps();
    }
}
