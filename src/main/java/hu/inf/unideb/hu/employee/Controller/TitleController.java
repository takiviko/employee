package hu.inf.unideb.hu.employee.Controller;

import hu.inf.unideb.hu.employee.Controller.DTO.Special.UpdateTitleRequestDTO;
import hu.inf.unideb.hu.employee.Controller.DTO.TitleDTO;
import hu.inf.unideb.hu.employee.Exception.DuplicateSalaryException;
import hu.inf.unideb.hu.employee.Exception.DuplicateTitleException;
import hu.inf.unideb.hu.employee.Exception.UnknownTitleException;
import hu.inf.unideb.hu.employee.Model.Title;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import hu.inf.unideb.hu.employee.Service.Interfaces.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/title")
public class TitleController {

    private final TitleService titleService;

    @GetMapping("/allTitles")
    public Collection<TitleDTO> readAllTitles() {
        return titleService.readAllTitles().stream()
                .map(this::convertTitleToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{empNo}")
    public Collection<TitleDTO> readTitlesByEmpNo(@RequestParam("empNo") int empNo) {
        try {
            return titleService.readAllTitlesByEmployee(empNo).stream()
                    .map(this::convertTitleToDTO)
                    .collect(Collectors.toList());
        } catch (UnknownTitleException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(path = "/")
    public void createTitle(TitleDTO titleDTO) {
        try {
            titleService.createTitle(convertDTOToTitle(titleDTO));
        } catch (DuplicateTitleException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/")
    public void deleteTitle(TitleKey titleKey) {
        try {
            titleService.deleteTitle(titleKey);
        } catch (UnknownTitleException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/")
    public void updateTitle(@Valid @RequestBody UpdateTitleRequestDTO updateTitleRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> log.error(objectError.toString()));
        }
        try {
            titleService.updateTitle(
                Title.builder()
                    .titleKey(
                        TitleKey.builder()
                            .empNo(updateTitleRequestDTO.getOldEmpNo())
                            .fromDate(updateTitleRequestDTO.getOldFromDate())
                            .title(updateTitleRequestDTO.getOldTitle())
                            .build()
                    )
                    .toDate(updateTitleRequestDTO.getOldToDate())
                    .build(),
                Title.builder()
                    .titleKey(
                        TitleKey.builder()
                            .empNo(updateTitleRequestDTO.getNewEmpNo())
                            .fromDate(updateTitleRequestDTO.getNewFromDate())
                            .title(updateTitleRequestDTO.getNewTitle())
                            .build()
                    )
                    .toDate(updateTitleRequestDTO.getNewToDate())
                    .build()

            );
        } catch (UnknownTitleException | DuplicateTitleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



    private TitleDTO convertTitleToDTO(Title title) {
        return TitleDTO.builder()
                .titleKey(title.getTitleKey())
                .toDate(title.getToDate())
                .build();
    }

    private Title convertDTOToTitle(TitleDTO titleDTO) {
        return Title.builder()
                .titleKey(titleDTO.getTitleKey())
                .toDate(titleDTO.getToDate())
                .build();
    }

}
