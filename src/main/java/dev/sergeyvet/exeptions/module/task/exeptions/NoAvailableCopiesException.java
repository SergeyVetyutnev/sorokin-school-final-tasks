package dev.sergeyvet.exeptions.module.task.exeptions;

public class NoAvailableCopiesException extends RuntimeException {
    public NoAvailableCopiesException(String message) {
        super(message);
    }
}
