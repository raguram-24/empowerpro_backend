package com.backend.empowerpro.exception;

public class LeaveNotFoundException extends RuntimeException{
    public LeaveNotFoundException(String message) {
        super(message);
    }
}
