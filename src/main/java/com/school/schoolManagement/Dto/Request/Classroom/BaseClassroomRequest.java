package com.school.schoolManagement.Dto.Request.Classroom;

import com.school.schoolManagement.Helper.ValidationMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseClassroomRequest {
    @NotNull(message = ValidationMessage.Classroom.SYMBOL_NOT_NULL)
    @NotEmpty(message = ValidationMessage.Classroom.SYMBOL_NOT_EMPTY)
    private String symbol;

    @NotNull(message = ValidationMessage.Classroom.SUPERVISOR_ID_NOT_NULL)
    private Long supervisorId;
}
