package com.school.schoolManagement.Dto.Request.Course;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest extends BaseCourseRequest{
    @NotNull(message = ValidationMessage.Course.NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Course.NAME_NOT_EMPTY)
    private String name;
}
