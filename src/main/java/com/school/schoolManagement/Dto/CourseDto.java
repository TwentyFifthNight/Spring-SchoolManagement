package com.school.schoolManagement.Dto;

public record CourseDto (
    Long id,
    String name,
    String dayOfWeek,
    String hour,
    Long classroomId,
    Long teacherId
) { }
