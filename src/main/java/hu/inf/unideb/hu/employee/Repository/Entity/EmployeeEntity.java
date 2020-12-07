package hu.inf.unideb.hu.employee.Repository.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EqualsAndHashCode
@Table(name="employees")
public class EmployeeEntity {

    @Id
    @Column(name="emp_no")
    private int empNo;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="gender")
    private String gender;

    @Column(name="hire_date")
    private Date hireDate;

}
