package com.school.schoolManagement.Dto.Request.Teacher;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeacherRequest extends BaseTeacherRequest {
    @NotNull(message = ValidationMessage.Teacher.TEACHER_PESEL_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Teacher.TEACHER_PESEL_NOT_EMPTY)
    @Pattern(regexp = ValidationMessage.General.PESEL_REGEX,
            message = ValidationMessage.Teacher.TEACHER_PESEL_NOT_VALID)
    private String pesel;
}
