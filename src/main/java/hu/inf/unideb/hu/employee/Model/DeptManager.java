package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptManager {
    String dept_no;
    int emp_no;
    Date from_date;
    Date to_date;
}
