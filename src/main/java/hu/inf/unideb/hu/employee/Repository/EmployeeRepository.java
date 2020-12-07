package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    boolean existsEmployeeEntityByEmpNo(int empNo);
    Optional<EmployeeEntity> findEmployeeEntityByEmpNo(int empNo);
    List<EmployeeEntity> findEmployeeEntityByGender(String gender);
}
