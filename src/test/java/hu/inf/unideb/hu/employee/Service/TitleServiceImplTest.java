package hu.inf.unideb.hu.employee.Service;

import hu.inf.unideb.hu.employee.Exception.DuplicateDepartmentException;
import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownDepartmentException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Department;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.DAO.Implementations.TitleDAOImpl;
import hu.inf.unideb.hu.employee.Repository.DAO.Interfaces.DepartmentDAO;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Service.Implementations.DepartmentServiceImpl;
import hu.inf.unideb.hu.employee.Service.Implementations.TitleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class TitleServiceImplTest {

    @InjectMocks
    private TitleServiceImpl titleService;
    @Mock
    TitleDAOImpl titleDAO;

    @Test
    public void testReadAllTitles() {
        when(titleDAO.readAllTitles()).thenReturn(getTitles());
        Collection<Title> actual = titleService.readAllTitles();
        assertThat(List.of(getTitle()), is(actual));
    }

    @Test
    public void testCreateTitle() throws DuplicateTitleException {
        Title title = getTitle();
        titleService.createTitle(title);
        verify(titleDAO, times(1)).createTitle(title);
    }

    @Test
    public void testCreateTitleWithDuplicateKey() throws DuplicateTitleException {
        doThrow(DuplicateTitleException.class).when(titleDAO).createTitle(any());
        assertThrows(DuplicateTitleException.class, () -> {
            titleService.createTitle(getTitle());
        });
    }

    @Test
    public void testDeleteTitle() throws UnknownTitleException {
        Title title = getTitle();
        titleService.deleteTitle(title.getTitleKey());
        verify(titleDAO, times(1)).deleteTitle(title.getTitleKey());
    }

    @Test
    public void testDeleteTitleWithUnknownTitle() throws UnknownTitleException {
        doThrow(UnknownTitleException.class).when(titleDAO).deleteTitle(any());
        assertThrows(UnknownTitleException.class, () -> {
            titleService.deleteTitle(getTitle().getTitleKey());
        });
    }

    @Test
    public void testUpdateTitle() throws UnknownTitleException, DuplicateTitleException {
        Title title1 = getTitle();
        Title title2 = getTitle2();
        titleService.updateTitle(title1, title2);
        verify(titleDAO, times(1)).updateTitle(title1, title2);
    }

    @Test
    public void testUpdateTitleWithUnknownTitle() throws UnknownTitleException, DuplicateTitleException {
        Title title1 = getTitle();
        Title title2 = getTitle2();
        doThrow(UnknownTitleException.class).when(titleDAO).updateTitle(any(), any());
        assertThrows(UnknownTitleException.class, () -> {
            titleService.updateTitle(title1, title2);
        });
    }

    @Test
    public void testGetTitlesByEmpNo() {
        when(titleDAO.readAllTitlesByEmployee (anyInt())).thenReturn(List.of(getTitle()));
        Collection<Title> titles = titleService.readAllTitlesByEmployee(getTitle().getTitleKey().getEmpNo());
        assertThat(List.of(getTitle()), is(titles));
    }



    private Title getTitle() {
        return Title.builder()
                .titleKey(
                        TitleKey.builder()
                                .empNo(10000)
                                .title("Developer")
                                .fromDate(Date.from(Instant.EPOCH))
                                .build()
                )
                .toDate(Date.from(Instant.EPOCH))
                .build();
    }

    private Title getTitle2() {
        return Title.builder()
                .titleKey(
                        TitleKey.builder()
                                .empNo(10001)
                                .title("Developer")
                                .fromDate(Date.from(Instant.EPOCH))
                                .build()
                )
                .toDate(Date.from(Instant.EPOCH))
                .build();
    }

    private Collection<Title> getTitles() {
      return List.of(getTitle());
    }

}
