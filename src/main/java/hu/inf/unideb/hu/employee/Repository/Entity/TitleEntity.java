package hu.inf.unideb.hu.employee.Repository.Entity;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
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
@Table(name="titles")
public class TitleEntity {

    @EmbeddedId
    private TitleKey titleKey;

    @Column(name="to_date")
    private Date toDate;

}
