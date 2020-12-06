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
    int empNo;
    Date birthDate;
    String firstName;
    String lastName;
    Gender gender;
    Date hireDate;
}
