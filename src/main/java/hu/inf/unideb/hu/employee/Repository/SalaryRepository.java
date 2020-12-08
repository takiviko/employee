package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import hu.inf.unideb.hu.employee.Repository.Entity.SalaryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends CrudRepository<SalaryEntity, Integer> {
    boolean existsSalaryEntityBySalaryKey(SalaryKey salaryKey);
    Optional<SalaryEntity> findSalaryEntityBySalaryKey(SalaryKey salaryKey);
    @Query("select a from SalaryEntity a where a.salaryKey.empNo = ?1")
    List<SalaryEntity> findSalaryEntitiesByEmpNo(int empNo);
}
