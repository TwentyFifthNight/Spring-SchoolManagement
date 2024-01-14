package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.ClassroomDto;
import com.school.schoolManagement.Dto.Converter.ClassroomDtoConverter;
import com.school.schoolManagement.Dto.Request.Classroom.CreateClassroomRequest;
import com.school.schoolManagement.Dto.Request.Classroom.UpdateClassroomRequest;
import com.school.schoolManagement.Exception.Classroom.ClassroomAlreadyExistException;
import com.school.schoolManagement.Exception.Classroom.ClassroomNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Model.Classroom;
import com.school.schoolManagement.Repository.ClassroomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class ClassroomServiceImpl implements ClassroomService{
    private final ClassroomRepository classroomRepository;
    private final TeacherService teacherService;
    private final ClassroomDtoConverter converter;
    private final ResourceBundle classroomLogMessage;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository,
                            TeacherService teacherService,
                            ClassroomDtoConverter converter) {
        this.classroomRepository = classroomRepository;
        this.teacherService = teacherService;
        this.converter = converter;

        classroomLogMessage = ResourceBundle.getBundle("i18n/ClassroomLogMessage", Locale.getDefault());
    }

    public void createClassroom(CreateClassroomRequest request) throws ClassroomAlreadyExistException{
        checkIfClassroomExists(request.getSymbol());

        Classroom classroom = new Classroom();
        classroom.setSymbol(request.getSymbol());
        classroom.setSupervisor(teacherService.findTeacherByTeacherId(request.getSupervisorId()));

        classroomRepository.save(classroom);
        log.info(classroomLogMessage.getString("classroomCreated"));
    }

    public void updateClassroom(Long id, UpdateClassroomRequest request) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        classroom.setSymbol(request.getSymbol());
        classroom.setSupervisor(teacherService.findTeacherByTeacherId(request.getSupervisorId()));

        classroomRepository.save(classroom);
        log.info(MessageFormat.format(classroomLogMessage.getString("classroomUpdated"), id));
    }

    public void deleteClassroom(Long id) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        classroomRepository.delete(classroom);
        log.info(MessageFormat.format(classroomLogMessage.getString("classroomDeleted"), id));
    }

    public ClassroomDto findClassroomById(Long id) throws ClassroomNotFoundException{
        Classroom classroom = findClassroomByClassroomId(id);

        log.info(MessageFormat.format(classroomLogMessage.getString("classroomFound"), id));
        return converter.convert(classroom);
    }

    public List<ClassroomDto> findAllClassrooms() throws ClassroomNotFoundException{
        List<Classroom> classroomList = classroomRepository.findAll();

        if (classroomList.isEmpty()) {
            log.info(classroomLogMessage.getString("classroomListEmpty"));
            throw new ClassroomNotFoundException(BusinessMessage.Classroom.CLASSROOM_LIST_EMPTY);
        }

        log.info(classroomLogMessage.getString("classroomListed"));
        return converter.convert(classroomList);
    }

    public Classroom findClassroomByClassroomId(Long id) throws ClassroomNotFoundException{
        return classroomRepository.findById(id).orElseThrow(() -> {
            log.info(MessageFormat.format(classroomLogMessage.getString("classroomNotFound"), id));
            return new ClassroomNotFoundException(BusinessMessage.Classroom.CLASSROOM_NOT_FOUND);
        });
    }

    private void checkIfClassroomExists(String symbol) throws ClassroomAlreadyExistException {
        if (classroomRepository.existsBySymbol(symbol)) {
            log.error(MessageFormat.format(classroomLogMessage.getString("classroomAlreadyExists"), symbol));
            throw new ClassroomAlreadyExistException(BusinessMessage.Classroom.CLASSROOM_ALREADY_EXISTS);
        }
    }
}
