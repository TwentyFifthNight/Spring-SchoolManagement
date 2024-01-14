package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;
import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<Void> createClassroom(@Valid CreateClassroomRequest request) {
        classroomService.createClassroom(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClassroom(@PathVariable Long id,
                                                @Valid UpdateClassroomRequest request) {
        classroomService.updateClassroom(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> findClassroomById(@PathVariable Long id) {
        return ResponseEntity.ok(classroomService.findClassroomById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> findAllClassrooms() {
        return ResponseEntity.ok(classroomService.findAllClassrooms());
    }
}
