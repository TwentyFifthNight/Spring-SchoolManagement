package com.school.schoolManagement.Dto;

public record ClassroomStudentDto(
        Long id,
        String firstName,
        String lastName,
        String pesel,
        String studentNumber
) { }
