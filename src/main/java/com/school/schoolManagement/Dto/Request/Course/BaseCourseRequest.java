package com.school.schoolManagement.Dto.Request.Course;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCourseRequest {
    @NotNull(message = ValidationMessage.Course.DAY_OF_WEEK_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Course.DAY_OF_WEEK_NOT_EMPTY)
    private String dayOfWeek;

    @NotNull(message = ValidationMessage.Course.HOUR_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Course.HOUR_NOT_EMPTY)
    @Pattern(regexp = ValidationMessage.General.HOUR_REGEX,
            message = ValidationMessage.Course.HOUR_NOT_VALID)
    private String hour;

    @NotNull(message = ValidationMessage.Course.CLASSROOM_ID_NOT_NULL)
    private Long classroomId;

    @NotNull(message = ValidationMessage.Course.TEACHER_ID_NOT_NULL)
    private Long teacherId;
}
