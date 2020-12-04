package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    int emp_no;
    int salary;
    Date from_date;
    Date to_date;
}
