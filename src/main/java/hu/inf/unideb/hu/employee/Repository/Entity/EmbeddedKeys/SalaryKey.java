package hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalaryKey implements Serializable {

    @Column(name="emp_no")
    private int empNo;

    @Column(name="from_date")
    private Date fromDate;
}
