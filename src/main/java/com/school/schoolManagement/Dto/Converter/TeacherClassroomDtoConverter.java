package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.TeacherClassroomDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherClassroomDtoConverter {
    public TeacherClassroomDto convert(Classroom from){
        return new TeacherClassroomDto(
                from.getId(),
                from.getName()
        );
    }
}
