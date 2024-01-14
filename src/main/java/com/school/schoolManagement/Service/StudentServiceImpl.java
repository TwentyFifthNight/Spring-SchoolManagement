package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Converter.StudentDtoConverter;
import com.school.schoolManagement.Dto.Request.Student.CreateStudentRequest;
import com.school.schoolManagement.Dto.Request.Student.UpdateStudentRequest;
import com.school.schoolManagement.Dto.StudentDto;
import com.school.schoolManagement.Exception.Student.StudentAlreadyExistException;
import com.school.schoolManagement.Exception.Student.StudentNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Helper.StudentNumberGenerator;
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
    private final AddressService addressService;
    private final StudentDtoConverter converter;
    private final ResourceBundle LogMessage;
    private final StudentNumberGenerator studentNumberGenerator;

    public StudentServiceImpl(StudentRepository studentRepository, ClassroomService classroomService,
                              AddressService addressService, StudentDtoConverter converter, StudentNumberGenerator studentNumberGenerator){
        this.studentRepository = studentRepository;
        this.classroomService = classroomService;
        this.addressService = addressService;
        this.converter = converter;
        this.studentNumberGenerator = studentNumberGenerator;

        LogMessage = ResourceBundle.getBundle("i18n/StudentLogMessage", Locale.getDefault());
    }

    public void createStudent(CreateStudentRequest request) throws StudentAlreadyExistException{
        checkIfStudentExists(request.getPesel());

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setStudentNumber(studentNumberGenerator.generate());
        student.setPesel(request.getPesel());
        student.setAddress(addressService.findAddressByAddressId(request.getAddressId()));

        studentRepository.save(student);
        log.info(LogMessage.getString("Created"));
    }

    public void updateStudent(Long id, UpdateStudentRequest request) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setClassroom(classroomService.findClassroomByClassroomId(request.getClassroomId()));

        studentRepository.save(student);
        log.info(MessageFormat.format(LogMessage.getString("Updated"), id));
    }

    public void addStudentToClassroom(Long id, Long classroomId) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);
        student.setClassroom(classroomService.findClassroomByClassroomId(classroomId));

        studentRepository.save(student);
        log.info(MessageFormat.format(LogMessage.getString("AddedToClassroom"), id, classroomId));
    }

    public void removeStudentFromClassroom(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);
        student.setClassroom(null);

        studentRepository.save(student);
        log.info(MessageFormat.format(LogMessage.getString("RemovedFromClassroom"), id));
    }

    public void deleteStudent(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        studentRepository.delete(student);
        log.info(MessageFormat.format(LogMessage.getString("Deleted"), id));
    }

    public StudentDto findStudentById(Long id) throws StudentNotFoundException{
        Student student = findStudentByStudentId(id);

        log.info(MessageFormat.format(LogMessage.getString("Found"), id));
        return converter.convert(student);
    }

    private void checkIfStudentExists(String pesel) throws StudentAlreadyExistException{
        if (studentRepository.existsByPesel(pesel)) {
            log.error(MessageFormat.format(LogMessage.getString("AlreadyExists"), pesel));
            throw new StudentAlreadyExistException(BusinessMessage.Student.ALREADY_EXISTS);
        }
    }

    private Student findStudentByStudentId(Long id) throws StudentNotFoundException{
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(MessageFormat.format(LogMessage.getString("NotFound"), id));
                    return new StudentNotFoundException(BusinessMessage.Student.NOT_FOUND);
                });
    }
}
