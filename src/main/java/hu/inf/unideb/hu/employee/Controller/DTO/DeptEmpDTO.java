package hu.inf.unideb.hu.employee.Controller.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DeptEmpDTO {
    int emp_no;
    String dept_no;
    Date from_date;
    Date to_date;
}
