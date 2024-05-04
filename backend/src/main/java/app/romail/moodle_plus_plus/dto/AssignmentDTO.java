
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AssignmentDTO {
private Long id;
    private String type;
    private String name;
    private String description;
    private List<Long> files_ids;
    private List<Long> submissions_ids;
    private Long subject_id;
    private String startDate;
    private String endDate;
    private String deadline;
    private int maxPoints;
}
