
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class CourseEnrollmentDTO {
private Long id;
    private Long course_id;
    private Long student_id;
    private List<CourseAttendanceDTO> courseAttendances;
    private List<GradeDTO> grades;
}
