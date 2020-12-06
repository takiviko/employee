package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Model.DeptManager;
import hu.inf.unideb.hu.employee.Repository.Entity.DeptManagerEntity;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptEmpKey;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeptManagerRepository extends CrudRepository<DeptManagerEntity, Integer> {

    boolean existsDeptManagerEntityByDeptManagerKey(DeptManagerKey deptManagerKey);
    Optional<DeptManagerEntity> findDeptManagerEntityByDeptManagerKey(DeptManagerKey deptManagerKey);

}
