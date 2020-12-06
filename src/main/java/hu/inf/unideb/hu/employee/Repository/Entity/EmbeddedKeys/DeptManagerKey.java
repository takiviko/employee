package hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DeptManagerKey implements Serializable {

    @Column(name="emp_no")
    private int empNo;

    @Column(name="dept_no")
    private String deptNo;

}
