package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Repository.Entity.SalaryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<SalaryEntity, Integer> {

}
