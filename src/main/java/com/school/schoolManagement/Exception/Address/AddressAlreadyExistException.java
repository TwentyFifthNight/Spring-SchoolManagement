package com.school.schoolManagement.Exception.Address;

public class AddressAlreadyExistException extends RuntimeException {
    public AddressAlreadyExistException(String message) {
        super(message);
    }
}