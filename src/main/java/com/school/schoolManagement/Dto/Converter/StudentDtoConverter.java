package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.StudentDto;
import com.school.schoolManagement.Model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDtoConverter {
    private final AddressDtoConverter addressDtoConverter;

    public StudentDtoConverter(AddressDtoConverter addressDtoConverter) {
        this.addressDtoConverter = addressDtoConverter;
    }

    public StudentDto convert(Student from) {
        return new StudentDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getPesel(),
                from.getStudentNumber(),
                addressDtoConverter.convert(from.getAddress()),
                from.getClassroom() != null ? from.getClassroom().getId() : null
        );
    }

    public List<StudentDto> convert(List<Student> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
