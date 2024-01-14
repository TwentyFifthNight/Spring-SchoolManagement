package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Converter.StudentDtoConverter;
import com.school.schoolManagement.Dto.Request.Student.CreateStudentRequest;
import com.school.schoolManagement.Dto.Request.Student.UpdateStudentRequest;
import com.school.schoolManagement.Dto.StudentDto;
import com.school.schoolManagement.Exception.Student.StudentAlreadyExistException;
import com.school.schoolManagement.Exception.Student.StudentNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Helper.StudentNumberGenerator;
import com.school.schoolManagement.Helper.StudentNumberGeneratorImpl;
import com.school.schoolManagement.Model.Student;
import com.school.schoolManagement.Repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final ClassroomService classroomService;
    private final StudentDtoConverter converter;
    private final ResourceBundle StudentLogMessage;
    private final StudentNumberGenerator studentNumberGenerator;

    public StudentServiceImpl(StudentRepository studentRepository, ClassroomService classroomService,
                              StudentDtoConverter converter){
        this.studentRepository = studentRepository;
        this.classroomService = classroomService;
        this.converter = converter;

        StudentLogMessage = ResourceBundle.getBundle("i18n/StudentLogMessage", Locale.getDefault());
        studentNumberGenerator = new StudentNumberGeneratorImpl();
    }

    public void createStudent(CreateStudentRequest request) throws StudentAlreadyExistException{
        checkIfStudentExists(request.getPesel());

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setStudentNumber(studentNumberGenerator.generate());
        student.setPesel(request.getPesel());

        studentRepository.save(student);
        log.info(StudentLogMessage.getString("studentCreated"));
    }

    public void updateStudent(Long id, UpdateStudentRequest request) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setClassroom(classroomService.findClassroomByClassroomId(request.getClassroomId()));

        studentRepository.save(student);
        log.info(MessageFormat.format(StudentLogMessage.getString("studentUpdated"), id));
    }

    public void addStudentToClassroom(Long id, Long classroomId) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);
        student.setClassroom(classroomService.findClassroomByClassroomId(classroomId));

        studentRepository.save(student);
        log.info(MessageFormat.format(StudentLogMessage.getString("studentAddedToClassroom"), id, classroomId));
    }

    public void removeStudentFromClassroom(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);
        student.setClassroom(null);

        studentRepository.save(student);
        log.info(MessageFormat.format(StudentLogMessage.getString("studentRemovedFromClassroom"), id));
    }

    public void deleteStudent(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        studentRepository.delete(student);
        log.info(MessageFormat.format(StudentLogMessage.getString("studentDeleted"), id));
    }

    public StudentDto findStudentById(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        log.info(MessageFormat.format(StudentLogMessage.getString("studentFound"), id));
        return converter.convert(student);
    }

    private void checkIfStudentExists(String pesel) throws StudentAlreadyExistException{
        if (studentRepository.existsByPesel(pesel)) {
            log.error(MessageFormat.format(StudentLogMessage.getString("studentAlreadyExists"), pesel));
            throw new StudentAlreadyExistException(BusinessMessage.Student.STUDENT_ALREADY_EXISTS);
        }
    }

    private Student findStudentByStudentId(Long id) throws StudentNotFoundException{
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(MessageFormat.format(StudentLogMessage.getString("studentNotFound"), id));
                    return new StudentNotFoundException(BusinessMessage.Student.STUDENT_NOT_FOUND);
                });
    }
}
