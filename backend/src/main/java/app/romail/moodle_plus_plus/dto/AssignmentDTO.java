
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AssignmentDTO {
private Long id;
    private String type;
    private String name;
    private String description;
    private Set<Long> files_ids;
    private Set<Long> submissions_ids;
    private Long subject_id;
    private Long startDate;
    private Long endDate;
    private Long deadline;
    private int maxPoints;
}
