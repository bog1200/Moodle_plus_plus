
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AssignmentSubmissionDTO {
private Long id;
    private Long assignment_id;
    private Long student_id;
    private Date submissionDate;
    private Date gradingDate;
    private Double grade;
    private Double maxGrade;
    private String submissionText;
}
