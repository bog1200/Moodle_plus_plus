
package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class TeacherDTO extends PersonDTO{
    private String title;
    private double salary;
    Set<Long> subjects_ids;
}
