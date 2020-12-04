package hu.inf.unideb.hu.employee.Repository.Entity;

import hu.inf.unideb.hu.employee.Model.Enums.Gender;
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
@Table(name="employees")
public class EmployeeEntity {

    @Id
    @Column(name="emp_no")
    private int emp_no;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private Gender gender;

    @Column(name="hire_date")
    private Date hireDate;

}
