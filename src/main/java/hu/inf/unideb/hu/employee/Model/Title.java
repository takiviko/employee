package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    int empNo;
    String title;
    Date fromDate;
    Date toDate;
}
