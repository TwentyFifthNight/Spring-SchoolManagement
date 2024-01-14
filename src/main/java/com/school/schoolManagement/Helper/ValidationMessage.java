package com.school.schoolManagement.Helper;

public class ValidationMessage {
    public static class General {
        public static final String PHONE_REGEX = "^(\\+[0-9]{1,3})?[0-9]{9}$";
        public static final String PESEL_REGEX = "^[0-9]{11}$";
    }

    public static class Address {
        public static final String HOUSE_NUMBER_NOT_EMPTY = "Address house number can not be empty";
        public static final String HOUSE_NUMBER_NOT_NULL = "Address house number can not be null";
        public static final String STREET_NOT_EMPTY = "Address street can not be empty";
        public static final String STREET_NOT_NULL = "Address street can not be null";
        public static final String CITY_NOT_EMPTY = "Address city can not be empty";
        public static final String CITY_NOT_NULL = "Address city can not be null";
        public static final String ZIPCODE_NOT_EMPTY = "Address zip code can not be empty";
        public static final String ZIPCODE_NOT_NULL = "Address zip code can not be null";
    }

    public static class Classroom {
        public static final String SYMBOL_NOT_EMPTY = "Classroom symbol can not be empty";
        public static final String SYMBOL_NOT_NULL = "Classroom symbol can not be null";
        public static final String SUPERVISOR_ID_NOT_NULL = "Classroom supervisor id can not be null";
    }

    public static class Student {
        public static final String FIRST_NAME_NOT_EMPTY = "Student first name can not be empty";
        public static final String FIRST_NAME_NOT_NULL = "Student first name can not be null";
        public static final String LAST_NAME_NOT_EMPTY = "Student last name can not be empty";
        public static final String LAST_NAME_NOT_NULL = "Student last name can not be null";
        public static final String PESEL_NOT_EMPTY = "Student pesel can not be empty";
        public static final String PESEL_NOT_NULL = "Student pesel can not be null";
        public static final String PESEL_NOT_VALID = "Student pesel is not valid, it should be 11 digits";
        public static final String CLASSROOM_ID_NOT_NULL = "Student classroom id can not be empty";
        public static final String ADDRESS_NOT_NULL = "Student address id can not be empty";
    }

    public static class Teacher {
        public static final String FIRST_NAME_NOT_EMPTY = "Teacher first name can not be empty";
        public static final String FIRST_NAME_NOT_NULL = "Teacher first name can not be null";
        public static final String LAST_NAME_NOT_EMPTY = "Teacher last name can not be empty";
        public static final String LAST_NAME_NOT_NULL = "Teacher last name can not be null";
        public static final String PESEL_NOT_EMPTY = "Teacher pesel can not be empty";
        public static final String PESEL_NOT_NULL = "Teacher pesel can not be null";
        public static final String PESEL_NOT_VALID = "Teacher pesel is not valid, it should be 11 digits";
        public static final String PHONE_NOT_EMPTY = "Teacher phone can not be empty";
        public static final String PHONE_NOT_NULL = "Teacher phone can not be null";
        public static final String PHONE_NOT_VALID = "Teacher phone is not valid";
    }
}
