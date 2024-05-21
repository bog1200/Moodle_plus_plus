
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class SubjectDTO {
private Long id;
    private String name;
    private String description;
    private String code;
    private Set<Long> teachers_ids;
    private Set<Long> studentGroups_ids;
    private Set<Long> assignments_ids;
    private Set<Long> courses_ids;
}
