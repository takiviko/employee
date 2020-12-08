package hu.inf.unideb.hu.employee.Repository.Entity;

import hu.inf.unideb.hu.employee.Model.Employee;
import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.SalaryKey;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
@Table(name="salaries")
public class SalaryEntity {

    @Column(name="salary")
    private int salary;

    @Column(name="to_date")
    private Date toDate;

    @EmbeddedId
    private SalaryKey salaryKey;

}
