package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;
import hu.inf.unideb.hu.employee.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import hu.inf.unideb.hu.employee.Repository.Entity.DepartmentEntity;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
public class DepartmentDAOImpl implements DepartmentDAO {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public String addDepartment(Department department) throws DuplicateDepartmentException {

        /*
        if(departmentRepository.existsDepartmentEntitiesByDept_name(department.getDept_name())) {
            throw new DuplicateDepartmentException("Department " + department.getDept_name() + "already exists");
        }

         */

        DepartmentEntity departmentEntity = DepartmentEntity.builder()
                .deptNo(department.getDeptNo())
                .deptName(department.getDeptName())
                .build();
        log.info("DepartmentEntity: " + departmentEntity.toString());

        try {
            departmentRepository.save(departmentEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return departmentEntity.getDeptName();
    }

    @Override
    @Transactional
    public void deleteDepartment(String deptName) throws UnknownDepartmentException {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByDeptName(deptName);
        if(departmentEntity.isEmpty()) {
            throw new UnknownDepartmentException("Department " + deptName + " not found");
        }

        try {
            departmentRepository.delete(departmentEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void updateDepartment(Department department) throws UnknownDepartmentException {

    }

    @Override
    public void createDepartment(Department department) throws DuplicateDepartmentException {
        if(departmentRepository.existsDepartmentEntityByDeptName(department.getDeptName())) {
            throw new DuplicateDepartmentException("Department " + department.getDeptName() + " already exists!");
        }

        DepartmentEntity departmentEntity = convertDepartmentToEntity(department);

        try {
            departmentRepository.save(departmentEntity);
            log.info("Department " + department.getDeptName() + " created");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentByName(String dept_name) throws UnknownDepartmentException {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findByDeptName(dept_name);
        if(departmentEntity.isEmpty()) {
            throw new UnknownDepartmentException("Department " + departmentEntity.toString() + " not found!");
        }
        return convertEntityToDepartment(departmentEntity.get());
    }

    @Override
    public Collection<Department> readAll() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), true)
                .map(this::convertEntityToDepartment)
                .collect(Collectors.toList());
    }

    protected Department convertEntityToDepartment(DepartmentEntity departmentEntity) {
        return Department.builder()
                .deptNo(departmentEntity.getDeptNo())
                .deptName(departmentEntity.getDeptName())
                .build();
    }

    protected DepartmentEntity convertDepartmentToEntity(Department department) {
        return DepartmentEntity.builder()
                .deptNo(department.getDeptNo())
                .deptName(department.getDeptName())
                .build();
    }
}
