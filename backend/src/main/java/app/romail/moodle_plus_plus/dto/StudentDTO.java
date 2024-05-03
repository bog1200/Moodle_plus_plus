
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class StudentDTO extends PersonDTO {
    private String studentId;
    private Date enrollmentDate;
}
