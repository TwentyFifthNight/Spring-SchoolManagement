package com.school.schoolManagement.Dto.Request.Student;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseStudentRequest {
    @NotNull(message = ValidationMessage.Student.STUDENT_FIRST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Student.STUDENT_FIRST_NAME_NOT_EMPTY)
    private String firstName;

    @NotNull(message = ValidationMessage.Student.STUDENT_LAST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Student.STUDENT_LAST_NAME_NOT_EMPTY)
    private String lastName;
}