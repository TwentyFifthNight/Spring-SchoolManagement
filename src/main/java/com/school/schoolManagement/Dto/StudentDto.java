package com.school.schoolManagement.Dto;

public record StudentDto(
        Long id,
        String firstName,
        String lastName,
        String pesel,
        String studentNumber,
        AddressDto addressList,
        String classroomId
) { }
