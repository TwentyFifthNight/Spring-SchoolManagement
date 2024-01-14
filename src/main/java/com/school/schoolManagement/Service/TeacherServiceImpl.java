package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Converter.TeacherDtoConverter;
import com.school.schoolManagement.Dto.Request.Teacher.CreateTeacherRequest;
import com.school.schoolManagement.Dto.Request.Teacher.UpdateTeacherRequest;
import com.school.schoolManagement.Dto.TeacherDto;
import com.school.schoolManagement.Exception.Teacher.TeacherAlreadyExistException;
import com.school.schoolManagement.Exception.Teacher.TeacherNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Model.Teacher;
import com.school.schoolManagement.Repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherDtoConverter converter;
    private final ResourceBundle teacherLogMessage;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                          TeacherDtoConverter converter) {
        this.teacherRepository = teacherRepository;
        this.converter = converter;

        teacherLogMessage = ResourceBundle.getBundle("i18n/TeacherLogMessage", Locale.getDefault());
    }

    public void createTeacher(CreateTeacherRequest request) throws TeacherAlreadyExistException{
        checkIfTeacherExists(request.getPesel());

        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setPesel(request.getPesel());
        teacher.setPhone(request.getPhone());

        teacherRepository.save(teacher);
        log.info(teacherLogMessage.getString("teacherCreated"));
    }

    public void updateTeacher(Long id, UpdateTeacherRequest request) throws TeacherNotFoundException{
        Teacher teacher = findTeacherByTeacherId(id);

        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setPhone(request.getPhone());

        teacherRepository.save(teacher);
        log.info(MessageFormat.format(teacherLogMessage.getString("teacherUpdated"), id));
    }

    public void deleteTeacher(Long id) throws TeacherNotFoundException{
        Teacher teacher = findTeacherByTeacherId(id);

        teacherRepository.delete(teacher);
        log.info(MessageFormat.format(teacherLogMessage.getString("teacherDeleted"), id));
    }

    public TeacherDto findTeacherById(Long id)  throws TeacherNotFoundException{
        Teacher teacher = findTeacherByTeacherId(id);

        log.info(MessageFormat.format(teacherLogMessage.getString("teacherFound"), id));
        return converter.convert(teacher);
    }

    private void checkIfTeacherExists(String pesel) throws TeacherAlreadyExistException{
        if (teacherRepository.existsByPesel(pesel)) {
            log.info(MessageFormat.format(teacherLogMessage.getString("teacherAlreadyExists"), pesel));
            throw new TeacherAlreadyExistException(BusinessMessage.Teacher.TEACHER_ALREADY_EXISTS);
        }
    }

    public Teacher findTeacherByTeacherId(Long id) throws TeacherNotFoundException{
        return teacherRepository.findById(id).orElseThrow(() -> {
            log.info(MessageFormat.format(teacherLogMessage.getString("teacherNotFound"), id));
            return new TeacherNotFoundException(BusinessMessage.Teacher.TEACHER_NOT_FOUND);
        });
    }
}
