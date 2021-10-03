package com.solbeg.task.exception;

import java.util.UUID;

public class NoSuchResourceException extends RuntimeException {

    private static final String MESSAGE = "%s with uuid %s doesn't exist";

    public NoSuchResourceException(String resourceName, UUID uuid) {
        super(String.format(MESSAGE, resourceName, uuid.toString()));
    }
}