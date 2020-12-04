package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmp {
    int emp_no;
    String dept_no;
    Date from_date;
    Date to_date;
}
