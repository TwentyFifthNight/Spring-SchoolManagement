package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.CourseDto;
import com.school.schoolManagement.Dto.Request.Course.CreateCourseRequest;
import com.school.schoolManagement.Dto.Request.Course.UpdateCourseRequest;

public interface CourseService {
    void createCourse(CreateCourseRequest request);
    void updateCourse(Long id, UpdateCourseRequest request);
    void deleteCourse(Long id);
    CourseDto findCourseById(Long id);
}
