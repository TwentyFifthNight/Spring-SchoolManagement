package com.school.schoolManagement.Helper;

public class BusinessMessage {
    public static class Address {
        public static final String NOT_FOUND = "Address not found";
        public static final String ALREADY_EXISTS = "Address already exists";
        public static final String CREATED_SUCCESSFULLY = "Address created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Address updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Address deleted successfully";
        public static final String RETRIEVED_SUCCESSFULLY = "Address retrieved successfully";
        public static final String LIST_EMPTY = "Address list is empty";
    }

    public static class Classroom {
        public static final String NOT_FOUND = "Classroom not found";
        public static final String ALREADY_EXISTS = "Classroom already exists";
        public static final String CREATED_SUCCESSFULLY = "Classroom created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Classroom updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Classroom deleted successfully";
        public static final String RETRIEVED_SUCCESSFULLY = "Classroom retrieved successfully";
        public static final String LIST_EMPTY = "Classroom list is empty";
        public static final String STUDENT_LIST_EMPTY = "Classroom student list is empty";
    }

    public static class Student {
        public static final String NOT_FOUND = "Student not found";
        public static final String ALREADY_EXISTS = "Student already exists";
        public static final String CREATED_SUCCESSFULLY = "Student created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Student updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Student deleted successfully";
        public static final String RETRIEVED_SUCCESSFULLY = "Student retrieved successfully";
    }

    public static class Teacher {
        public static final String NOT_FOUND = "Teacher not found";
        public static final String ALREADY_EXISTS = "Teacher already exists";
        public static final String CREATED_SUCCESSFULLY = "Teacher created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Teacher updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Teacher deleted successfully";
        public static final String RETRIEVED_SUCCESSFULLY = "Teacher retrieved successfully";
    }

    public static class Course {
        public static final String NOT_FOUND = "Course not found";
        public static final String ALREADY_EXISTS_BY_CLASSROOM = "Assigned classroom already have classes at given time";
        public static final String ALREADY_EXISTS_BY_TEACHER = "Assigned teacher already conduct classes at given time";
        public static final String CREATED_SUCCESSFULLY = "Course created successfully";
        public static final String UPDATED_SUCCESSFULLY = "Course updated successfully";
        public static final String DELETED_SUCCESSFULLY = "Course deleted successfully";
        public static final String RETRIEVED_SUCCESSFULLY = "Course retrieved successfully";
        public static final String INVALID_DAY_OF_WEEK = "Course day of week must be valid(MONDAY, TUESDAY...)";
        public static final String INVALID_HOUR = "Course hour must be valid(12:00, 9:45...)";
    }
}
