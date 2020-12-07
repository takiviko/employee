package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;

import java.util.Collection;

public interface TitleDAO {

    void addTitle(Title title) throws DuplicateTitleException;

    void UpdateTitle(Title oldTitle, Title newTitle) throws UnknownTitleException;

    void deleteTitle(int empNo) throws UnknownTitleException;

    Collection<Title> getAllTitles();

    Collection<Title> getAllTitlesByEmployee(int empNo);

}
