
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class CourseAttendanceDTO {
    private Long id;
    private Long course_id;
    private Long subjectEnrollment_id;
    private Long date;
}
