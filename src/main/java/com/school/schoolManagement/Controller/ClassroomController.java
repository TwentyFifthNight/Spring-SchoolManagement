package com.school.schoolManagement.Controller;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;

import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Service.ClassroomService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/{id}/student-list")
    public void generateStudentListPDF(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_PDF.getType());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy:hh:mm:ss");

        response.setHeader("Content-Disposition", "attachment; filename=pdf_" +
                dateFormat.format(new Date()) + ".pdf");

        classroomService.generateStudentListPDF(response, id);
    }
}
