package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.Request.Teacher.CreateTeacherRequest;
import com.school.schoolManagement.Dto.Request.Teacher.UpdateTeacherRequest;
import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Void> createTeacher(@RequestBody @Valid CreateTeacherRequest request) {
        teacherService.createTeacher(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable Long id,
                                              @RequestBody @Valid UpdateTeacherRequest request) {
        teacherService.updateTeacher(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable Long id) {
        return new ResponseEntity<>(teacherService.findTeacherById(id), HttpStatus.OK);
    }
}