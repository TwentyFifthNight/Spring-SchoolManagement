package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Model.Classroom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassroomDtoConverter {
    private final ClassroomStudentDtoConverter classroomStudentDtoConverter;

    public ClassroomDtoConverter(ClassroomStudentDtoConverter classroomStudentDtoConverter) {
        this.classroomStudentDtoConverter = classroomStudentDtoConverter;
    }

    public ClassroomDto convert(Classroom from) {
        return new ClassroomDto(
                from.getId(),
                from.getSymbol(),
                classroomStudentDtoConverter.convert(from.getStudentList()),
                from.getSupervisor().getId()
        );
    }

    public List<ClassroomDto> convert(List<Classroom> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
