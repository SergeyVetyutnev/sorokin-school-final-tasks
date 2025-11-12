package dev.sergeyvet.exeptions.module.task.exeptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
