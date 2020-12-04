package hu.inf.unideb.hu.employee.Service.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;

import java.util.Collection;

public interface TitleService {

    void addTitle(Title title) throws DuplicateTitleException;

    void UpdateTitle(Title title) throws UnknownTitleException;

    void deleteTitle(Title title) throws UnknownTitleException;

    Collection<Title> getAllTitles();

    Collection<Title> getAllTitlesByEmployee(int emp_no);


}
