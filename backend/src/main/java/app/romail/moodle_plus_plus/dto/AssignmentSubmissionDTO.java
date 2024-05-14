
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AssignmentSubmissionDTO {
    private Long id;
    private Long assignment_id;
    private Long student_id;
    private Long submissionDate;
    private Long grade_id;
    private String submissionText;
}
