package hu.inf.unideb.hu.employee.Repository;

import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Repository.Entity.TitleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TitleRepository extends CrudRepository<TitleEntity, Integer> {
    boolean existsTitleEntityByTitleKey(TitleKey titleKey);
    Optional<TitleEntity> findTitleEntityByTitleKey(TitleKey titleKey);
    @Query("select a from TitleEntity a where a.titleKey.empNo = ?1")
    List<TitleEntity> findTitleEntitiesByEmpNo(int empNo);
}
