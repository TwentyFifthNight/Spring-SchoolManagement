package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Model.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherDtoConverter {
    private final TeacherClassroomDtoConverter teacherClassroomDtoConverter;

    public TeacherDtoConverter(TeacherClassroomDtoConverter teacherClassroomDtoConverter) {
        this.teacherClassroomDtoConverter = teacherClassroomDtoConverter;
    }

    public TeacherDto convert(Teacher from) {
        return new TeacherDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getPesel(),
                from.getPhone(),
                from.getSupervisedClass() != null ? teacherClassroomDtoConverter.convert(from.getSupervisedClass()) : null
        );
    }

    public List<TeacherDto> convert(List<Teacher> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
