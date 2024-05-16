
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class CourseDTO {
    private Long id;
    private Long startDate;
    private Long endDate;
    private Long subject_id;
    private Set<Long> courseAttendances_ids = new HashSet<>();
}
