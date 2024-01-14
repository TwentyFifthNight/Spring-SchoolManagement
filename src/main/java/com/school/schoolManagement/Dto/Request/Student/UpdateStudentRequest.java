package com.school.schoolManagement.Dto.Request.Student;

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
public class UpdateStudentRequest extends BaseStudentRequest {
    @NotNull(message = ValidationMessage.Student.STUDENT_CLASSROOM_ID_NOT_EMPTY)
    @NotEmpty(message = ValidationMessage.Student.STUDENT_CLASSROOM_ID_NOT_EMPTY)
    private Long classroomId;
}
