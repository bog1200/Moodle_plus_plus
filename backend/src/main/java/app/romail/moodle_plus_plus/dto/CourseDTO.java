
package app.romail.moodle_plus_plus.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDTO {
private Long id;
    private Date startDate;
    private Date endDate;
    private List<CourseAttendanceDTO> courseAttendances = new ArrayList<>();
    private Double maxGrade;
}
