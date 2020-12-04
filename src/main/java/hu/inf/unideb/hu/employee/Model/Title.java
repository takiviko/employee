package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    int emp_no;
    String title;
    Date from_date;
    Date to_date;
}
