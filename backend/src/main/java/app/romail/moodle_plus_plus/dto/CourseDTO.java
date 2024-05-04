
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class CourseDTO {
private Long id;
    private String startDate;
    private String endDate;
    private Long subject_id;
    private List<Long> courseAttendances_ids = new ArrayList<>();
}
