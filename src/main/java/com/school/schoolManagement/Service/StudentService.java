package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Request.Student.CreateStudentRequest;
import com.school.schoolManagement.Dto.Request.Student.UpdateStudentRequest;
import com.school.schoolManagement.Dto.StudentDto;

public interface StudentService {
    void createStudent(CreateStudentRequest request);

    void updateStudent(Long id, UpdateStudentRequest request);

    void addStudentToClassroom(Long id, Long classroomId);

    void removeStudentFromClassroom(Long id);

    void deleteStudent(Long id);

    StudentDto findStudentById(Long id);
}
