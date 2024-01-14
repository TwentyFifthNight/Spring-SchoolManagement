package com.school.schoolManagement.Helper;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class StudentNumberGeneratorImpl implements StudentNumberGenerator{
    private static final AtomicLong idCounter = new AtomicLong();
    @Override
    public String generate() {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
