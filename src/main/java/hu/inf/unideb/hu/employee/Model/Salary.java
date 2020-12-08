package hu.inf.unideb.hu.employee.Model;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import lombok.*;

import javax.persistence.Id;
import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    int salary;
    Date toDate;
    SalaryKey salaryKey;
}
