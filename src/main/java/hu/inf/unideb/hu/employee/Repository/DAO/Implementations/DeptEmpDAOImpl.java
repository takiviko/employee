package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptEmpDAO;
import hu.inf.unideb.hu.employee.Repository.DeptEmpRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.DeptEmpEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
public class DeptEmpDAOImpl implements DeptEmpDAO {

    private final DeptEmpRepository deptEmpRepository;

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
        return StreamSupport.stream(deptEmpRepository.findAll().spliterator(), true)
                .map(this::convertEntityToDeptEmp)
                .collect(Collectors.toList());
    }

    protected DeptEmp convertEntityToDeptEmp(DeptEmpEntity deptEmpEntity) {
        return DeptEmp.builder()
                .dept_no(deptEmpEntity.getDept_no())
                .emp_no(deptEmpEntity.getEmp_no())
                .from_date(deptEmpEntity.getFromDate())
                .to_date(deptEmpEntity.getToDate())
                .build();
    }
}
