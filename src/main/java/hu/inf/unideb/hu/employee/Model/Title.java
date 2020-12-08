package hu.inf.unideb.hu.employee.Model;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import lombok.*;

import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    private TitleKey titleKey;
    private Date toDate;
}
