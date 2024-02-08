package com.school.schoolManagement.Service;

import com.school.schoolManagement.Dto.Converter.CourseDtoConverter;
import com.school.schoolManagement.Dto.CourseDto;
import com.school.schoolManagement.Dto.Request.Course.CreateCourseRequest;
import com.school.schoolManagement.Dto.Request.Course.UpdateCourseRequest;
import com.school.schoolManagement.Exception.Course.CourseAlreadyExistException;
import com.school.schoolManagement.Exception.Course.CourseIllegalArgumentException;
import com.school.schoolManagement.Exception.Course.CourseNotFoundException;
import com.school.schoolManagement.Helper.BusinessMessage;
import com.school.schoolManagement.Model.Classroom;
import com.school.schoolManagement.Model.Course;
import com.school.schoolManagement.Model.Teacher;
import com.school.schoolManagement.Repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final ClassroomService classroomService;
    private final TeacherService teacherService;
    private final CourseDtoConverter converter;
    private final ResourceBundle LogMessage;

    public CourseServiceImpl(CourseRepository courseRepository, ClassroomService classroomService,
                              TeacherService teacherService, CourseDtoConverter converter){
        this.courseRepository = courseRepository;
        this.classroomService = classroomService;
        this.teacherService = teacherService;
        this.converter = converter;

        LogMessage = ResourceBundle.getBundle("i18n/CourseLogMessage", Locale.getDefault());
    }

    @Override
    public void createCourse(CreateCourseRequest request) throws CourseAlreadyExistException, CourseIllegalArgumentException{
        DayOfWeek dayOfWeek = parseDayOfWeek(request.getDayOfWeek());
        LocalTime hour = parseHour(request.getHour());
        Classroom classroom = classroomService.findClassroomByClassroomId(request.getClassroomId());
        Teacher teacher = teacherService.findTeacherByTeacherId(request.getTeacherId());


        checkIfCourseExists(dayOfWeek, hour, classroom, teacher);

        Course course = new Course();
        course.setName(request.getName());
        course.setDayOfWeek(dayOfWeek);
        course.setHour(hour);
        course.setClassroom(classroom);
        course.setTeacher(teacher);

        courseRepository.save(course);
        log.info(LogMessage.getString("Created"));
    }

    @Override
    public void updateCourse(Long id, UpdateCourseRequest request) throws CourseNotFoundException, CourseIllegalArgumentException {
        Course course = findCourseByCourseId(id);
        course.setDayOfWeek(parseDayOfWeek(request.getDayOfWeek()));
        course.setHour(parseHour(request.getHour()));
        course.setClassroom(classroomService.findClassroomByClassroomId(request.getClassroomId()));
        course.setTeacher(teacherService.findTeacherByTeacherId(request.getTeacherId()));

        courseRepository.save(course);
        log.info(MessageFormat.format(LogMessage.getString("Updated"), id));
    }

    @Override
    public void deleteCourse(Long id) throws CourseNotFoundException{
        Course course = findCourseByCourseId(id);

        courseRepository.delete(course);
        log.info(MessageFormat.format(LogMessage.getString("Deleted"), id));
    }

    @Override
    public CourseDto findCourseById(Long id) throws CourseNotFoundException{
        Course course = findCourseByCourseId(id);

        log.info(MessageFormat.format(LogMessage.getString("Found"), id));
        return converter.convert(course);
    }

    private void checkIfCourseExists(DayOfWeek dayOfWeek, LocalTime time, Classroom classroom, Teacher teacher) throws CourseAlreadyExistException {
        if (courseRepository.existsByDayOfWeekAndHourAndClassroom(dayOfWeek, time, classroom)) {
            log.error(MessageFormat.format(LogMessage.getString("AlreadyExistsByClassroom"), classroom.getId()));
            throw new CourseAlreadyExistException(BusinessMessage.Course.ALREADY_EXISTS_BY_CLASSROOM);
        }
        if(courseRepository.existsByDayOfWeekAndHourAndTeacher(dayOfWeek, time, teacher)) {
            log.error(MessageFormat.format(LogMessage.getString("AlreadyExistsByTeacher"), teacher.getId()));
            throw new CourseAlreadyExistException(BusinessMessage.Course.ALREADY_EXISTS_BY_TEACHER);
        }
    }

    private Course findCourseByCourseId(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(MessageFormat.format(LogMessage.getString("NotFound"), id));
                    return new CourseNotFoundException(BusinessMessage.Course.NOT_FOUND);
                });
    }

    private DayOfWeek parseDayOfWeek(String dayOfWeek){
        try {
            return DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        }catch (IllegalArgumentException ex){
            log.info(MessageFormat.format(LogMessage.getString("InvalidDayOfWeek"), dayOfWeek));
            throw new CourseIllegalArgumentException(BusinessMessage.Course.INVALID_DAY_OF_WEEK);
        }
    }

    private LocalTime parseHour(String hour){
        try {
            return LocalTime.parse(hour);
        }catch (DateTimeParseException ex){
            log.info(MessageFormat.format(LogMessage.getString("InvalidHour"), hour));
            throw new CourseIllegalArgumentException(BusinessMessage.Course.INVALID_HOUR);
        }
    }
}
