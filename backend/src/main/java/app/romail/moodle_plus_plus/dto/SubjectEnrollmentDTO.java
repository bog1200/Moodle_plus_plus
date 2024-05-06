
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class SubjectEnrollmentDTO {
private Long id;
    private Long subject_id;
    private Long student_id;
    private List<Long> courseAttendances_ids;
}
