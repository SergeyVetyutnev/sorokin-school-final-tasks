package dev.sergeyvet.exeptions.module.task;

import dev.sergeyvet.exeptions.module.task.exceptions.BookNotFoundException;
import dev.sergeyvet.exeptions.module.task.exceptions.NoAvailableCopiesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private final List<Book> catalog = new ArrayList<>();

    public void printBooks(){
        catalog.forEach(book -> System.out.println(book));
    }
    public List<Book> getAllBooks(){
        return catalog;
    }

    public void addBook(String title, String author, int copies){

        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("название книги не должно быть пустым");
        }
        if (author == null || author.trim().isEmpty()){
            throw new IllegalArgumentException("имя автора не должно быть пустым");
        }
        if (copies < 0){
            throw new IllegalArgumentException("количество книг не может быть меньше 0");
        }

        Book book = new Book(title, author, copies);
        catalog.add(book);
    }

    public void takeBook(String title){
        Book book = findBookByTitle(title).
                orElseThrow(() ->new BookNotFoundException(String.format("книга '%s' не найдена", title)));

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
        Book book = findBookByTitle(title).
                orElseThrow(() ->new BookNotFoundException(String.format("книга '%s' не найдена", title)));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        System.out.println("Книга успешно возвращена");
    }

    private Optional<Book> findBookByTitle(String title){

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("название книги для поиска не может быть пустым");
        }

        for (Book book: catalog) {
            if (book.getTitle().equals(title)){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }
}
