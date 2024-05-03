
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private List<Long> files;
    private Long subject_id;
    private Date startDate;
    private Date endDate;
    private Date deadline;
    private int maxPoints;
}
