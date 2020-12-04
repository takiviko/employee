package hu.inf.unideb.hu.employee.Model;

import hu.inf.unideb.hu.employee.Model.Enums.Gender;
import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    int emp_no;
    Date birth_date;
    String first_name;
    String last_name;
    Gender gender;
    Date hire_date;
}
