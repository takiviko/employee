package hu.inf.unideb.hu.employee.Controller.DTO;

import hu.inf.unideb.hu.employee.Repository.Entity.EmbeddedKeys.TitleKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TitleDTO {
    private TitleKey titleKey;
    private Date toDate;
}
