package hu.inf.unideb.hu.employee.Controller.DTO.Special;

import hu.inf.unideb.hu.employee.Model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {
    int oldEmpNo;
    Date oldBirthDate;
    String oldFirstName;
    String oldLastName;
    Gender oldGender;
    Date oldHireDate;

    int newEmpNo;
    Date newBirthDate;
    String newFirstName;
    String newLastName;
    Gender newGender;
    Date newHireDate;
}
