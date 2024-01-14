package com.school.schoolManagement.Dto;

import java.util.List;

public record ClassroomDto(
        Long id,
        String symbol,
        List<ClassroomStudentDto> studentList,
        Long supervisorId
) { }
