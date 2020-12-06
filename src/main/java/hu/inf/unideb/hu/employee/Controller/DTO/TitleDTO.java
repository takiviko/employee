package hu.inf.unideb.hu.employee.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TitleDTO {
    int empNo;
    String title;
    Date fromDate;
    Date toDate;
}
