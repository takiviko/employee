package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.DeptEmpEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptEmpRepository extends CrudRepository<DeptEmpEntity, Integer> {
    boolean existsDeptEmpEntityByDeptEmpKey(DeptEmpKey deptEmpKey);
    Optional<DeptEmpEntity> findByDeptEmpKey(DeptEmpKey deptEmpKey);
}
