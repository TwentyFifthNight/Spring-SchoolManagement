package com.school.schoolManagement.Repository;

import com.school.schoolManagement.Model.Classroom;
import com.school.schoolManagement.Model.Course;
import com.school.schoolManagement.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByDayOfWeekAndHourAndClassroom(DayOfWeek dayOfWeek, LocalTime time, Classroom classroom);
    boolean existsByDayOfWeekAndHourAndTeacher(DayOfWeek dayOfWeek, LocalTime time, Teacher teacher);
}
