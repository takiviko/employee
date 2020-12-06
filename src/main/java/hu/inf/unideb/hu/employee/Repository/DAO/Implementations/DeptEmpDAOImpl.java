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
                        .empNo(deptEmp.getDeptEmpKey().getEmpNo())
                        .deptNo(deptEmp.getDeptEmpKey().getDeptNo())
                        .build()))
        {
            throw new DuplicateDeptEmpException("DeptEmp " + deptEmp.getDeptEmpKey()  +  ", " + deptEmp.getDeptEmpKey() + " already exists");
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
    public void updateDeptEmp(DeptEmp oldDeptEmp, DeptEmp newDeptEmp) throws UnknownDeptEmpException, DuplicateDeptEmpException {
        Optional<DeptEmpEntity> oldDeptEmpEntity = deptEmpRepository.findByDeptEmpKey(oldDeptEmp.getDeptEmpKey());
        if(oldDeptEmpEntity.isEmpty()) {
            throw new UnknownDeptEmpException("DeptEmp " + oldDeptEmp.getDeptEmpKey() + " not found");
        }
        if(deptEmpRepository.existsDeptEmpEntityByDeptEmpKey(newDeptEmp.getDeptEmpKey())) {
            throw new DuplicateDeptEmpException("DeptEmp " + newDeptEmp.getDeptEmpKey() + " already exists");
        }

        DeptEmpEntity newDeptEmpEntity = convertDeptEmpToEntity(newDeptEmp);

        log.info("DeptEmp" + oldDeptEmp.getDeptEmpKey() + " updated");
        try {
            deptEmpRepository.save(newDeptEmpEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public DeptEmp getDeptEmp(DeptEmpKey deptEmpKey) throws UnknownDeptEmpException {
        Optional<DeptEmpEntity> deptEmpEntity = deptEmpRepository.findByDeptEmpKey(deptEmpKey);
        if(deptEmpEntity.isEmpty()) {
            throw new UnknownDeptEmpException("DeptEmp " + deptEmpKey + " not found");
        }
        log.info("Getting deptEmp by " + deptEmpKey);
        return convertEntityToDeptEmp(deptEmpEntity.get());
    }

    @Override
    public Collection<DeptEmp> getAllDeptEmps() {
        return StreamSupport.stream(deptEmpRepository.findAll().spliterator(), true)
                .map(this::convertEntityToDeptEmp)
                .collect(Collectors.toList());
    }

    protected DeptEmp convertEntityToDeptEmp(DeptEmpEntity deptEmpEntity) {
        return DeptEmp.builder()
                .deptEmpKey(deptEmpEntity.getDeptEmpKey())
                .fromDate(deptEmpEntity.getFromDate())
                .toDate(deptEmpEntity.getToDate())
                .build();
    }

    protected DeptEmpEntity convertDeptEmpToEntity(DeptEmp deptEmp) {
        return DeptEmpEntity.builder()
                .deptEmpKey(DeptEmpKey.builder()
                        .deptNo(deptEmp.getDeptEmpKey().getDeptNo())
                        .empNo(deptEmp.getDeptEmpKey().getEmpNo())
                        .build())
                .fromDate(deptEmp.getFromDate())
                .toDate(deptEmp.getToDate())
                .build();
    }
}
