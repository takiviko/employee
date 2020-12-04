package hu.inf.unideb.hu.employee.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Builder
public class Department {

    String deptNo;

    @Id
    String deptName;
}
