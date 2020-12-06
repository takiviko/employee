package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptEmpException;
import hu.inf.unideb.hu.employee.Exception.UnknownEmployeeException;
import hu.inf.unideb.hu.employee.Model.DeptEmp;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptEmpDAO;
import hu.inf.unideb.hu.employee.Repository.DeptEmpRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.DeptEmpEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
public class DeptEmpDAOImpl implements DeptEmpDAO {

    private final DeptEmpRepository deptEmpRepository;

    @Override
    public void createDeptEmp(DeptEmp deptEmp) throws DuplicateDeptEmpException {
        if(
                deptEmpRepository.existsDeptEmpEntityByDeptEmpKey(DeptEmpKey.builder()
                        .empNo(deptEmp.getEmpNo())
                        .deptNo(deptEmp.getDeptNo())
                        .build()))
        {
            throw new DuplicateDeptEmpException("DeptEmp " + deptEmp.getEmpNo()  +  ", " + deptEmp.getDeptNo() + " already exists");
        }
        DeptEmpEntity deptEmpEntity = convertDeptEmpToEntity(deptEmp);

        try {
            deptEmpRepository.save(deptEmpEntity);
            log.info("DeptEmp " + deptEmp.toString() + " created");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException {
        Optional<DeptEmpEntity> deptEmpEntity = deptEmpRepository.findByDeptEmpKey(deptEmpKey);
        if(deptEmpEntity.isEmpty()) {
            throw new UnknownDeptEmpException("DeptEmp " + deptEmpKey.toString() + " does not exist");
        }
        try {
            log.info("DeptEmp " + deptEmpKey + " deleted");
            deptEmpRepository.delete(deptEmpEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateDeptEmp(DeptEmp deptEmp) throws UnknownDeptEmpException, UnknownEmployeeException {

    }

    @Override
    public DeptEmp getDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException {
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
                .deptNo(deptEmpEntity.getDeptEmpKey().getDeptNo())
                .empNo(deptEmpEntity.getDeptEmpKey().getEmpNo())
                .fromDate(deptEmpEntity.getFromDate())
                .toDate(deptEmpEntity.getToDate())
                .build();
    }

    protected DeptEmpEntity convertDeptEmpToEntity(DeptEmp deptEmp) {
        return DeptEmpEntity.builder()
                .deptEmpKey(DeptEmpKey.builder()
                        .deptNo(deptEmp.getDeptNo())
                        .empNo(deptEmp.getEmpNo())
                        .build())
                .fromDate(deptEmp.getFromDate())
                .toDate(deptEmp.getToDate())
                .build();
    }
}
