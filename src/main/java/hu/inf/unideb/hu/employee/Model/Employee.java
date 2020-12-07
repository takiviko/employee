package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    int empNo;
    Date birthDate;
    String firstName;
    String lastName;
    String gender;
    Date hireDate;
}
