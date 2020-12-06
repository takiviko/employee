package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    int empNo;
    int salary;
    Date fromDate;
    Date toDate;
}
