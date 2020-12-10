package hu.inf.unideb.hu.employee.Repository.DAO;

import hu.inf.unideb.hu.employee.Exception.*;
import hu.inf.unideb.hu.employee.Model.Salary;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.DAO.Implementations.TitleDAOImpl;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Repository.Entity.TitleEntity;
import hu.inf.unideb.hu.employee.Repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TitleDAOImplTest {

    @Spy
    @InjectMocks
    private TitleDAOImpl titleDAO;

    @Mock
    private TitleRepository titleRepository;

    @Test
    public void testCreateTitle() throws DuplicateTitleException {
        titleDAO.createTitle(getTitle());
        verify(titleRepository, times(1)).save(any());
    }

    @Test
    public void testCreateTitleWithExistingTitle() throws DuplicateTitleException {
        doThrow(DuplicateTitleException.class).when(titleDAO).createTitle(any());
        assertThrows(DuplicateTitleException.class, () -> {
            titleDAO.createTitle(getTitle());
        });
    }

    @Test
    public void testCreateTitleWithExistingTitle2() throws DuplicateTitleException {
        doReturn(true).when(titleRepository).existsTitleEntityByTitleKey(any());
        assertThrows(DuplicateTitleException.class, () -> {
            titleDAO.createTitle(getTitle());
        });
    }

    @Test
    public void testReadAll() {
        titleDAO.readAllTitles();
        verify(titleRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteTitle() throws UnknownTitleException {
        doReturn(Optional.of(getTitleEntity())).when(titleRepository).findTitleEntityByTitleKey(any());
        titleDAO.deleteTitle(getTitle().getTitleKey());
        verify(titleRepository, times(1)).findTitleEntityByTitleKey(getTitle().getTitleKey());
    }

    @Test
    public void testDeleteTitleWithUnknownTitle() throws UnknownTitleException {
        doReturn(Optional.empty()).when(titleRepository).findTitleEntityByTitleKey(any());
        assertThrows(UnknownTitleException.class, () -> titleDAO.deleteTitle(getTitle().getTitleKey()));
        verify(titleRepository, times(1)).findTitleEntityByTitleKey(any());
    }

    @Test
    public void testUpdateTitle() throws UnknownTitleException, DuplicateTitleException {
        Title title = getTitle();
        doReturn(Optional.of(title)).when(titleRepository).findTitleEntityByTitleKey(any());
        titleDAO.updateTitle(title, title);
        verify(titleRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateTitleWithUnknownTitle() throws UnknownTitleException, DuplicateTitleException {
        Title title1 = getTitle();
        Title title2 = getTitle2();
        assertThrows(UnknownTitleException.class, () -> {
            titleDAO.updateTitle(title1, title2);
        });
    }

    @Test
    public void testGetTitlesByEmpNo() throws UnknownTitleException, DuplicateTitleException {
        when(titleRepository.findTitleEntitiesByEmpNo(anyInt())).thenReturn(List.of(getTitleEntity()));
        Collection<Title> titles = titleDAO.readAllTitlesByEmployee(getTitle().getTitleKey().getEmpNo());
        assertEquals(List.of(getTitle()), titles);
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

    private TitleEntity getTitleEntity() {
        return TitleEntity.builder()
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


}
