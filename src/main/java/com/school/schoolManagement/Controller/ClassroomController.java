package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;
import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Service.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<Void> createClassroom(@RequestBody @Valid CreateClassroomRequest request) {
        classroomService.createClassroom(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClassroom(@PathVariable Long id,
                                                @RequestBody @Valid UpdateClassroomRequest request) {
        classroomService.updateClassroom(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> findClassroomById(@PathVariable Long id) {
        return new ResponseEntity<>(classroomService.findClassroomById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> findAllClassrooms() {
        return new ResponseEntity<>(classroomService.findAllClassrooms(), HttpStatus.OK);
    }
}
