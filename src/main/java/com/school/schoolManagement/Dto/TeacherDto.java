package com.school.schoolManagement.Dto;

public record TeacherDto(
        Long id,
        String firstName,
        String lastName,
        String pesel,
        String phone,
        TeacherClassroomDto supervisedClass
) { }
