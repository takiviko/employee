package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.TitleDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Slf4j
public class TitleServiceImpl implements TitleService {

    private final TitleDAO titleDAO;

    @Override
    public void createTitle(Title title) throws DuplicateTitleException {
        titleDAO.createTitle(title);
    }

    @Override
    public void updateTitle(Title oldTitle, Title newTitle) throws UnknownTitleException, DuplicateTitleException {
        titleDAO.updateTitle(oldTitle, newTitle);
    }

    @Override
    public void deleteTitle(TitleKey titleKey) throws UnknownTitleException {
        titleDAO.deleteTitle(titleKey);
    }

    @Override
    public Collection<Title> readAllTitles() {
        return titleDAO.readAllTitles();
    }

    @Override
    public Collection<Title> readAllTitlesByEmployee(int empNo) {
        return titleDAO.readAllTitlesByEmployee(empNo);
    }
}
