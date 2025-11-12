package dev.sergeyvet.exeptions.module.task.exceptions;

public class NoAvailableCopiesException extends RuntimeException {
    public NoAvailableCopiesException(String message) {
        super(message);
    }
}
