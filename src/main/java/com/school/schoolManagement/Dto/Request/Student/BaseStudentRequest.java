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
    @NotNull(message = ValidationMessage.Student.FIRST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Student.FIRST_NAME_NOT_EMPTY)
    private String firstName;

    @NotNull(message = ValidationMessage.Student.LAST_NAME_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Student.LAST_NAME_NOT_EMPTY)
    private String lastName;

    @NotNull(message = ValidationMessage.Student.ADDRESS_NOT_NULL)
    private Long addressId;
}
