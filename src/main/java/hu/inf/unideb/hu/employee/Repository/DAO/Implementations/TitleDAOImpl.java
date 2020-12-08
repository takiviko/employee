package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.TitleDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Repository.Entity.TitleEntity;
import hu.inf.unideb.hu.employee.Repository.SalaryRepository;
import hu.inf.unideb.hu.employee.Repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TitleDAOImpl implements TitleDAO {

    private final TitleRepository titleRepository;

    @Override
    public void createTitle(Title title) throws DuplicateTitleException {
        if(titleRepository.existsTitleEntityByTitleKey(title.getTitleKey())) {
            throw new DuplicateTitleException("Title " + title.getTitleKey().toString() + " already exists");
        }

        TitleEntity titleEntity = convertTitleToEntity(title);

        log.info("Title " + title.getTitleKey() + " created");
        try {
            titleRepository.save(titleEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void UpdateTitle(Title oldTitle, Title newTitle) throws UnknownTitleException {
        Optional<TitleEntity> oldTitleEntity = titleRepository.findTitleEntityByTitleKey(oldTitle.getTitleKey());
        if(oldTitleEntity.isEmpty()) {
            throw new UnknownTitleException("Title " + oldTitle.getTitleKey() + " does not exist");
        }

        TitleEntity titleEntity = convertTitleToEntity(newTitle);
        titleEntity.setTitleKey(oldTitle.getTitleKey());

        log.info("Title " + oldTitle.getTitleKey() + " updated");

        try {
            titleRepository.save(titleEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteTitle(TitleKey titleKey) throws UnknownTitleException {
        Optional<TitleEntity> titleEntity = titleRepository.findTitleEntityByTitleKey(titleKey);
        if(titleEntity.isEmpty()) {
            throw new UnknownTitleException("Title " + titleKey + " does not exist");
        }

        log.info("Title " + titleKey + " deleted");
        try {
            titleRepository.delete(titleEntity.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Collection<Title> readAllTitles() {
        return StreamSupport.stream(titleRepository.findAll().spliterator(), true)
                .map(this::convertEntityToTitle)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Title> readAllTitlesByEmployee(int empNo) {
        return titleRepository.findTitleEntitiesByEmpNo(empNo).parallelStream()
                .map(this::convertEntityToTitle)
                .collect(Collectors.toList());
    }

    private Title convertEntityToTitle(TitleEntity titleEntity) {
        return Title.builder()
                .titleKey(
                        TitleKey.builder()
                                .empNo(titleEntity.getTitleKey().getEmpNo())
                                .fromDate(titleEntity.getTitleKey().getFromDate())
                                .title(titleEntity.getTitleKey().getTitle())
                                .build()
                )
                .toDate(titleEntity.getToDate())
                .build();
    }

    private TitleEntity convertTitleToEntity(Title title) {
        return TitleEntity.builder()
                .titleKey(
                        TitleKey.builder()
                                .empNo(title.getTitleKey().getEmpNo())
                                .fromDate(title.getTitleKey().getFromDate())
                                .title(title.getTitleKey().getTitle())
                                .build()
                )
                .toDate(title.getToDate())
                .build();
    }
}
