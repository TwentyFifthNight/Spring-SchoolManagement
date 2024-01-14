package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.TeacherClassroomDto;
import com.school.schoolManagement.Model.Classroom;
import org.springframework.stereotype.Component;

@Component
public class TeacherClassroomDtoConverter {
    public TeacherClassroomDto convert(Classroom from){
        return new TeacherClassroomDto(
                from.getId(),
                from.getSymbol()
        );
    }
}
