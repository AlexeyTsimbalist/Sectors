package com.solbeg.sectorsapp.exception;

public class NoSuchResourceException extends RuntimeException {

    private static final String MESSAGE = "%s with id %d doesn't exist";

    public NoSuchResourceException(String resourceName, Long id) {
        super(String.format(MESSAGE, resourceName, id));
    }
}