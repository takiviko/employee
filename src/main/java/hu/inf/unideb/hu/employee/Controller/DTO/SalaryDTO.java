package hu.inf.unideb.hu.employee.Controller.DTO;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    int salary;
    Date toDate;
    SalaryKey salaryKey;
}
