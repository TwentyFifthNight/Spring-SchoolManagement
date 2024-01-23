package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;
import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Model.Classroom;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ClassroomService {
    void createClassroom(CreateClassroomRequest request);

    void updateClassroom(Long id, UpdateClassroomRequest request);

    void deleteClassroom(Long id);

    ClassroomDto findClassroomById(Long id);

    Classroom findClassroomByClassroomId(Long id);

    List<ClassroomDto> findAllClassrooms();

    void generateStudentListPDF(HttpServletResponse response, Long id) throws IOException;
}
