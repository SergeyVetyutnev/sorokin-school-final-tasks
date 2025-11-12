package dev.sergeyvet.exeptions.module.task;

import dev.sergeyvet.exeptions.module.task.exeptions.BookNotFoundException;
import dev.sergeyvet.exeptions.module.task.exeptions.NoAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> catalog = new ArrayList<>();

    public void printBooks(){
        catalog.forEach(book -> System.out.println(book.toString()));
    }

    public void addBook(Book book){
        catalog.add(book);
    }

    public void takeBook(String title){
        Book book = findBookByTitle(title);
        if (book == null){
            throw new BookNotFoundException(String.format("книга '%s' не найдена", title));
        }
        int availableCopies = book.getAvailableCopies();
        if (availableCopies <= 0){
            throw new  NoAvailableCopiesException(String.format("не удалось взять книгу '%s' доступных копий '%d'",
                    title, availableCopies));
                }else {
                    book.setAvailableCopies(availableCopies - 1);
                    System.out.println("Книга успешно взята");
                }
    }

    public void returnBook(String title){
        Book book = findBookByTitle(title);
        if (book == null){
            throw new BookNotFoundException(String.format("книга '%s' не найдена", title));
        }
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        System.out.println("Книга успешно возвращена");
    }

    private Book findBookByTitle(String title){
        for (Book book: catalog) {
            if (book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }

}
