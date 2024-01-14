package com.school.schoolManagement.Exception.Teacher;

public class TeacherAlreadyExistException extends RuntimeException {
    public TeacherAlreadyExistException(String message) {
        super(message);
    }
}
