package com.school.schoolManagement.Dto.Request.Teacher;

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
public class BaseTeacherRequest {
    @NotNull(message = ValidationMessage.Teacher.FIRST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Teacher.FIRST_NAME_NOT_EMPTY)
    private String firstName;

    @NotNull(message = ValidationMessage.Teacher.LAST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Teacher.LAST_NAME_NOT_EMPTY)
    private String lastName;

    @NotNull(message = ValidationMessage.Teacher.PHONE_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Teacher.PHONE_NOT_EMPTY)
    @Pattern(regexp = ValidationMessage.General.PHONE_REGEX,
            message = ValidationMessage.Teacher.PHONE_NOT_VALID)
    private String phone;

}
