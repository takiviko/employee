package hu.inf.unideb.hu.employee.Repository.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
@Table(name="salaries")
public class SalaryEntity {

    @Id
    @Column(name="emp_no")
    private int empNo;

    @Column(name="salary")
    private int salary;

    @Column(name="from_date")
    private Date fromDate;

    @Column(name="to_date")
    private Date toDate;

}
