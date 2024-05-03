
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class CourseAttendanceDTO {
private Long id;
    private Long courseEnrollment_id;
    private Date date;
}
