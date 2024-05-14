
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class StudentGroupDTO {
private Long id;
    private String name;
    private List<StudentDTO> students;
    private List<SubjectDTO> subjects;
}
