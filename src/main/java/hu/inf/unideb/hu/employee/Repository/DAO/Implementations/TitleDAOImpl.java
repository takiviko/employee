package hu.inf.unideb.hu.employee.Repository.DAO.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.TitleDAO;
import hu.inf.unideb.hu.employee.Repository.SalaryRepository;
import hu.inf.unideb.hu.employee.Repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RequiredArgsConstructor
@Repository
public class TitleDAOImpl implements TitleDAO {

    private final TitleRepository titleRepository;

    @Override
    public void addTitle(Title title) throws DuplicateTitleException {

    }

    @Override
    public void UpdateTitle(Title oldTitle, Title newTitle) throws UnknownTitleException {

    }

    @Override
    public void deleteTitle(int empNo) throws UnknownTitleException {

    }

    @Override
    public Collection<Title> getAllTitles() {
        return null;
    }

    @Override
    public Collection<Title> getAllTitlesByEmployee(int empNo) {
        return null;
    }
}
