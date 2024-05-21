
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class StudentGroupDTO {
private Long id;
    private String name;
    private Set<StudentDTO> students;
    private Set<SubjectDTO> subjects;
}
