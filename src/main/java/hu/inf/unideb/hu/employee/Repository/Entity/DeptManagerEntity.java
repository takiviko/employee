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
public class DeptManagerEntity {

    @EmbeddedId
    private DeptManagerKey deptManagerKey;

    @Column(name="from_date")
    private Date fromDate;

    @Column(name="to_date")
    private Date toDate;

}
