package hu.inf.unideb.hu.employee.Service.Implementations;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Service.Interfaces.TitleService;

import java.util.Collection;

public class TitleServiceImpl implements TitleService {

    @Override
    public void addTitle(Title title) throws DuplicateTitleException {

    }

    @Override
    public void UpdateTitle(Title title) throws UnknownTitleException {

    }

    @Override
    public void deleteTitle(Title title) throws UnknownTitleException {

    }

    @Override
    public Collection<Title> getAllTitles() {
        return null;
    }

    @Override
    public Collection<Title> getAllTitlesByEmployee(int emp_no) {
        return null;
    }
}
