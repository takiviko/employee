package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.DeptEmpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptEmpRepository extends CrudRepository<DeptEmpEntity, Integer> {

}
