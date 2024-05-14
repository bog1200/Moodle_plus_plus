
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class GradeDTO {
private Long id;
    private Double grade;
    private Double maxGrade;
    private String comment;
    private String date;
    private Long assignmentSubmission_id;
}
