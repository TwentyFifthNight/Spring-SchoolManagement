package com.school.schoolManagement.Dto.Converter;

import com.school.schoolManagement.Dto.CourseDto;
import com.school.schoolManagement.Model.Course;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CourseDtoConverter {
    public CourseDto convert(Course from){
        return new CourseDto(
                from.getId(),
                from.getName(),
                from.getDayOfWeek().toString(),
                from.getHour().format(DateTimeFormatter.ofPattern("hh:mm")),
                from.getClassroom() != null ? from.getClassroom().getId() : null,
                from.getTeacher() != null ? from.getTeacher().getId() : null
        );
    }
}
