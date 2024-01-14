package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.Request.Student.CreateStudentRequest;
import com.school.schoolManagement.Dto.Request.Student.UpdateStudentRequest;
import com.school.schoolManagement.Exception.Student.StudentAlreadyExistException;
import com.school.schoolManagement.Exception.Student.StudentNotFoundException;
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
    public ResponseEntity<Object> createStudent(@Valid CreateStudentRequest request) {
        try {
            studentService.createStudent(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (StudentAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Long id,
                                              @Valid UpdateStudentRequest request) {
        try {
            studentService.updateStudent(id, request);
            return ResponseEntity.ok().build();
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/classroom/{classroomId}")
    public ResponseEntity<Object> addStudentToClassroom(@PathVariable Long id, @PathVariable Long classroomId) {
        try {
            studentService.addStudentToClassroom(id, classroomId);
            return ResponseEntity.ok().build();
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/classroom/remove")
    public ResponseEntity<Object> removeStudentFromClassroom(@PathVariable Long id) {
        try {
            studentService.removeStudentFromClassroom(id);
            return ResponseEntity.ok().build();
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        try{
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.findStudentById(id));
        }catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}