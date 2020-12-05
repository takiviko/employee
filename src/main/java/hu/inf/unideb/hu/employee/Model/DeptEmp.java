package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeptEmp {
    int empNo;
    String deptNo;
    Date fromDate;
    Date toDate;
}
