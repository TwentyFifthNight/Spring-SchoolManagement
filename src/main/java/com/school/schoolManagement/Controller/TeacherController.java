package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.Request.Teacher.CreateTeacherRequest;
import com.school.schoolManagement.Dto.Request.Teacher.UpdateTeacherRequest;
import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Service.TeacherService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Void> createTeacher(@Valid CreateTeacherRequest request) {
        teacherService.createTeacher(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable Long id,
                                              @Valid UpdateTeacherRequest request) {
        teacherService.updateTeacher(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> findTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }
}