package dev.sergeyvet.collections.module.task.exceptions;

public class ContactAlreadyExistException extends RuntimeException {
    public ContactAlreadyExistException(String message) {
        super(message);
    }
}
