package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Request.Teacher.CreateTeacherRequest;
import com.school.schoolManagement.Dto.Request.Teacher.UpdateTeacherRequest;
import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Model.Teacher;

public interface TeacherService {
    public void createTeacher(CreateTeacherRequest request);

    public void updateTeacher(Long id, UpdateTeacherRequest request);

    public void deleteTeacher(Long id);

    public TeacherDto findTeacherById(Long id);
    Teacher findTeacherByTeacherId(Long id);
}
