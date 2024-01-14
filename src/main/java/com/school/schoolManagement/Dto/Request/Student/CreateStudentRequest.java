package com.school.schoolManagement.Dto.Request.Student;

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
public class CreateStudentRequest extends BaseStudentRequest {
    @NotNull(message = ValidationMessage.Student.STUDENT_PESEL_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Student.STUDENT_PESEL_NOT_EMPTY)
    @Pattern(regexp = ValidationMessage.General.PESEL_REGEX,
            message = ValidationMessage.Student.STUDENT_PESEL_NOT_VALID)
    private String pesel;
}