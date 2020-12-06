package hu.inf.unideb.hu.employee.Controller.DTO;

import hu.inf.unideb.hu.employee.Model.Enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
        int empNo;
        Date birthDate;
        String firstName;
        String lastName;
        Gender gender;
        Date hireDate;
}
