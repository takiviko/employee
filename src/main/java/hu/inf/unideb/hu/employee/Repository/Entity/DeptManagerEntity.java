package hu.inf.unideb.hu.employee.Repository.Entity;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.DeptManagerKey;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
@Table(name="dept_manager")
@IdClass(DeptManagerKey.class)
public class DeptManagerEntity {

    @Id
    @Column(name="emp_no")
    private int emp_no;

    @Id
    @Column(name="dept_no")
    private String dept_no;

    @Column(name="from_date")
    private Date fromDate;

    @Column(name="to_date")
    private Date toDate;

}
