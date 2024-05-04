
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class SubjectDTO {
private Long id;
    private String name;
    private String description;
    private String code;
    private List<Long> teachers_ids;
    private List<Long> studentGroups_ids;
    private List<Long> assignments_ids;
    private List<Long> courses_ids;
}
