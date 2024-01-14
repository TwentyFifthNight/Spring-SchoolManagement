package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Request.Teacher.CreateTeacherRequest;
import com.school.schoolManagement.Dto.Request.Teacher.UpdateTeacherRequest;
import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Model.Teacher;

public interface TeacherService {
    void createTeacher(CreateTeacherRequest request);

    void updateTeacher(Long id, UpdateTeacherRequest request);

    void deleteTeacher(Long id);

    TeacherDto findTeacherById(Long id);
    Teacher findTeacherByTeacherId(Long id);
}
