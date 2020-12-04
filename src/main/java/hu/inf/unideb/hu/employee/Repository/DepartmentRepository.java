package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {

    Optional<DepartmentEntity> findByDeptName(String name);
    boolean existsDepartmentEntityByDeptName(String deptName);

}
