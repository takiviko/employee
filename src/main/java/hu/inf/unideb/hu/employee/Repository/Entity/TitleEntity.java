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
@Table(name="titles")
public class TitleEntity {

    @Id
    @Column(name="emp_no")
    private int emp_no;

    @Column(name="title")
    private String title;

    @Column(name="from_date")
    private Date fromDate;

    @Column(name="to_date")
    private Date toDate;

}
