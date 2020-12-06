package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

}
