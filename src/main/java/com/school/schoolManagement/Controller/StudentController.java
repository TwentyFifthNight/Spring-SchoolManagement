package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.Request.Student.CreateStudentRequest;
import com.school.schoolManagement.Dto.Request.Student.UpdateStudentRequest;
import com.school.schoolManagement.Service.StudentService;
import com.school.schoolManagement.Service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid CreateStudentRequest request) {
        studentService.createStudent(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id,
                                                @RequestBody @Valid UpdateStudentRequest request) {
        studentService.updateStudent(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/classroom/{classroomId}")
    public ResponseEntity<Object> addStudentToClassroom(@PathVariable Long id, @PathVariable Long classroomId) {
        studentService.addStudentToClassroom(id, classroomId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/classroom/remove")
    public ResponseEntity<Object> removeStudentFromClassroom(@PathVariable Long id) {
        studentService.removeStudentFromClassroom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findStudentById(id), HttpStatus.OK);
    }

}