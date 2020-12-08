package hu.inf.unideb.hu.employee.Repository.DAO.Interfaces;

import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;

import java.util.Collection;

public interface TitleDAO {

    void createTitle(Title title) throws DuplicateTitleException;

    void UpdateTitle(Title oldTitle, Title newTitle) throws UnknownTitleException, DuplicateTitleException;

    void deleteTitle(TitleKey titleKey) throws UnknownTitleException;

    Collection<Title> readAllTitles();

    Collection<Title> readAllTitlesByEmployee(int empNo);

}
