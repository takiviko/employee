package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDeptManagerException;
import hu.inf.unideb.hu.employee.Exception.UnknownDeptManagerException;
import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DeptManagerDAO;
import hu.inf.unideb.hu.employee.Repository.DeptManagerRepository;
import hu.inf.unideb.hu.employee.Repository.Entity.DeptManagerEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
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
public class DeptManagerDAOImpl implements DeptManagerDAO {

    private final DeptManagerRepository deptManagerRepository;

    @Override
    public void createDeptManager(DeptManager deptManager) throws DuplicateDeptManagerException {
        if(
                deptManagerRepository.existsDeptManagerEntityByDeptManagerKey(
                        DeptManagerKey.builder()
                        .empNo(deptManager.getDeptManagerKey().getEmpNo())
                        .deptNo(deptManager.getDeptManagerKey().getDeptNo())
                        .build()))
        {
            throw new DuplicateDeptManagerException("DeptManager " + deptManager.getDeptManagerKey()  +  ", " + deptManager.getDeptManagerKey() + " already exists");
        }
        DeptManagerEntity deptManagerEntity = convertDeptManagerToEntity(deptManager);

        try {
            deptManagerRepository.save(deptManagerEntity);
            log.info("DeptManager " + deptManager.toString() + " created");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteDeptManager(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException {
        Optional<DeptManagerEntity> deptManagerEntity = deptManagerRepository.findDeptManagerEntityByDeptManagerKey(deptManagerKey);
        if(deptManagerEntity.isEmpty()) {
            throw new UnknownDeptManagerException("DeptManager " + deptManagerKey.toString() + " does not exist");
        }
        try {
            log.info("DeptManager " + deptManagerKey + " deleted");
            deptManagerRepository.delete(deptManagerEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateDeptManager(DeptManager oldDeptManager, DeptManager newDeptManager) throws UnknownDeptManagerException, DuplicateDeptManagerException {
        Optional<DeptManagerEntity> oldDeptManagerEntity = deptManagerRepository.findDeptManagerEntityByDeptManagerKey(oldDeptManager.getDeptManagerKey());
        if(oldDeptManagerEntity.isEmpty()) {
            throw new UnknownDeptManagerException("DeptManager " + oldDeptManager.getDeptManagerKey() + " not found");
        }
        if(deptManagerRepository.existsDeptManagerEntityByDeptManagerKey(newDeptManager.getDeptManagerKey())) {
            throw new DuplicateDeptManagerException("DeptManager " + newDeptManager.getDeptManagerKey() + " already exists");
        }

        DeptManagerEntity newDeptManagerEntity = convertDeptManagerToEntity(newDeptManager);

        log.info("DeptManager " + oldDeptManager.getDeptManagerKey() + " updated");
        try {
            deptManagerRepository.save(newDeptManagerEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public DeptManager getDeptManagerByKey(DeptManagerKey deptManagerKey) throws UnknownDeptManagerException {
        Optional<DeptManagerEntity> deptManagerEntity = deptManagerRepository.findDeptManagerEntityByDeptManagerKey(deptManagerKey);
        if(deptManagerEntity.isEmpty()) {
            throw new UnknownDeptManagerException("DeptManager " + deptManagerKey + " not found");
        }
        log.info("Getting DeptManager by " + deptManagerKey);
        return convertEntityToDeptManager(deptManagerEntity.get());
    }

    @Override
    public Collection<DeptManager> getAllManagers() {
        return StreamSupport.stream(deptManagerRepository.findAll().spliterator(), true)
                .map(this::convertEntityToDeptManager)
                .collect(Collectors.toList());
    }

    protected DeptManager convertEntityToDeptManager(DeptManagerEntity deptManagerEntity) {
        return DeptManager.builder()
                .deptManagerKey(deptManagerEntity.getDeptManagerKey())
                .fromDate(deptManagerEntity.getFromDate())
                .toDate(deptManagerEntity.getToDate())
                .build();
    }

    protected DeptManagerEntity convertDeptManagerToEntity(DeptManager deptManager) {
        return DeptManagerEntity.builder()
                .deptManagerKey(
                        DeptManagerKey.builder()
                        .deptNo(deptManager.getDeptManagerKey().getDeptNo())
                        .empNo(deptManager.getDeptManagerKey().getEmpNo())
                        .build())
                .fromDate(deptManager.getFromDate())
                .toDate(deptManager.getToDate())
                .build();
    }
}
