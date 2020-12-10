package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {

    Optional<DepartmentEntity> findByDeptName(String name);
    Optional<DepartmentEntity> findByDeptNo(String deptNo);
    boolean existsDepartmentEntityByDeptName(String deptName);
    boolean existsDepartmentEntityByDeptNo(String deptNo);

}
