package hu.inf.unideb.hu.employee.Controller.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
        String gender;
        Date hireDate;
}
