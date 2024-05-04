
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class TeacherDTO extends PersonDTO{
    private String title;
    private double salary;
    List<Long> subjects_ids;
}
