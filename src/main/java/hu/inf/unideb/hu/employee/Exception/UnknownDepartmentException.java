package hu.inf.unideb.hu.employee.Exception;

import hu.inf.unideb.hu.employee.Model.Department;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnknownDepartmentException extends Exception {
    public UnknownDepartmentException(String message) {
        super(message);
    }
}
