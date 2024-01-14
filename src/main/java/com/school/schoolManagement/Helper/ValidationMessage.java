package com.school.schoolManagement.Helper;

public class ValidationMessage {
    public static class General {
        public static final String PHONE_REGEX = "^[09]{2}[0-9]{9}$";
        public static final String PESEL_REGEX = "^[0-9]{11}$";
    }

    public static class Address {
        public static final String ADDRESS_HOUSE_NUMBER_NOT_EMPTY = "Address house number can not be empty";
        public static final String ADDRESS_HOUSE_NUMBER_NOT_NULL = "Address house number can not be null";
        public static final String ADDRESS_STREET_NOT_EMPTY = "Address street can not be empty";
        public static final String ADDRESS_STREET_NOT_NULL = "Address street can not be null";
        public static final String ADDRESS_CITY_NOT_EMPTY = "Address city can not be empty";
        public static final String ADDRESS_CITY_NOT_NULL = "Address city can not be null";
        public static final String ADDRESS_ZIPCODE_NOT_EMPTY = "Address zip code can not be empty";
        public static final String ADDRESS_ZIPCODE_NOT_NULL = "Address zip code can not be null";

    }

    public static class Student {
        public static final String STUDENT_FIRST_NAME_NOT_EMPTY = "Student first name can not be empty";
        public static final String STUDENT_FIRST_NAME_NOT_NULL = "Student first name can not be null";
        public static final String STUDENT_LAST_NAME_NOT_EMPTY = "Student last name can not be empty";
        public static final String STUDENT_LAST_NAME_NOT_NULL = "Student last name can not be null";
        public static final String STUDENT_PESEL_NOT_EMPTY = "Student pesel can not be empty";
        public static final String STUDENT_PESEL_NOT_NULL = "Student pesel can not be null";
        public static final String STUDENT_PESEL_NOT_VALID = "Student pesel is not valid, it should be 11 digits";
        public static final String STUDENT_CLASSROOM_ID_NOT_EMPTY = "Student classroom id can not be empty";
    }

    public static class Teacher {
        public static final String TEACHER_FIRST_NAME_NOT_EMPTY = "Teacher first name can not be empty";
        public static final String TEACHER_FIRST_NAME_NOT_NULL = "Teacher first name can not be null";
        public static final String TEACHER_LAST_NAME_NOT_EMPTY = "Teacher last name can not be empty";
        public static final String TEACHER_LAST_NAME_NOT_NULL = "Teacher last name can not be null";
        public static final String TEACHER_PESEL_NOT_EMPTY = "Teacher pesel can not be empty";
        public static final String TEACHER_PESEL_NOT_NULL = "Teacher pesel can not be null";
        public static final String TEACHER_PESEL_NOT_VALID = "Teacher pesel is not valid, it should be 11 digits";
        public static final String TEACHER_PHONE_NOT_EMPTY = "Teacher phone can not be empty";
        public static final String TEACHER_PHONE_NOT_NULL = "Teacher phone can not be null";
        public static final String TEACHER_PHONE_NOT_VALID = "Teacher phone is not valid";
    }
}
