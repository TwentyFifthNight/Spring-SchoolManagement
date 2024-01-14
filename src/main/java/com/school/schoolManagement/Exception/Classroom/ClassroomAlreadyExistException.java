package com.school.schoolManagement.Exception.Classroom;

public class ClassroomAlreadyExistException extends RuntimeException {
    public ClassroomAlreadyExistException(String message) {
        super(message);
    }
}
