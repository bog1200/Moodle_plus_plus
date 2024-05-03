
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
    private List<TeacherDTO> teachers;
    private List<StudentGroupDTO> studentGroups;
    private List<AssignmentDTO> assignments;
    private List<CourseDTO> courses;
}
