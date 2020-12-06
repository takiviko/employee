package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTitleRequestDTO {

    int oldEmpNo;
    String oldTitle;
    Date oldFromDate;
    Date oldToDate;

    int newEmpNo;
    String newTitle;
    Date newFromDate;
    Date newToDate;
}
