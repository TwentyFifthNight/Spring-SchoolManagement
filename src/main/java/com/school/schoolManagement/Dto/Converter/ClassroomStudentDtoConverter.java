package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.ClassroomStudentDto;
import com.school.schoolManagement.Model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassroomStudentDtoConverter {
    public ClassroomStudentDto convert(Student from){
        return new ClassroomStudentDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getPesel(),
                from.getStudentNumber()
        );
    }

    public List<ClassroomStudentDto> convert(List<Student> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
