package com.school.schoolManagement.Dto;

public record AddressDto(
        Long id,
        String houseNumber,
        String street,
        String city,
        String zipCode
) { }
