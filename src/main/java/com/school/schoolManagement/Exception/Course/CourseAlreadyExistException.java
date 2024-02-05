package com.school.schoolManagement.Exception.Course;

public class CourseAlreadyExistException extends RuntimeException{
    public CourseAlreadyExistException(String message) {
        super(message);
    }
}
