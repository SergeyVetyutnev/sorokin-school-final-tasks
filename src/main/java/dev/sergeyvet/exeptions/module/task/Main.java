package dev.sergeyvet.exeptions.module.task;

import dev.sergeyvet.exeptions.module.task.exceptions.BookNotFoundException;
import dev.sergeyvet.exeptions.module.task.exceptions.NoAvailableCopiesException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Library library = new Library();

        //заполнение библиотеки книгами
        for (int i = 0; i < 10; i++) {
            library.addBook(new Book("title" + i,
                    "author" + i,
                    random.nextInt(0,3)));
        }


        while (true){
            try {
                System.out.print("введите номер действия (0 - показать действия): ");
                int userChose = scanner.nextInt();
                scanner.nextLine();

                switch (userChose){
                    case 0 -> printActions();
                    case 1 -> library.printBooks();
                    case 2 -> {
                        System.out.print("введите название книги: ");
                        String title = scanner.nextLine();

                        System.out.print("введите автора: ");
                        String author = scanner.nextLine();

                        System.out.print("введите кол-во копий: ");
                        int availableCopies = scanner.nextInt();
                        scanner.nextLine();

                        library.addBook(new Book(title, author, availableCopies));
                        System.out.println("книга успешно добавлена");

                    }
                    case 3 -> {
                        System.out.print("введите название книги: ");
                        String title = scanner.nextLine();
                        library.takeBook(title);

                    }
                    case 4 -> {
                        System.out.print("введите название книги: ");
                        String title = scanner.nextLine();
                        library.returnBook(title);

                    }
                    case 5 -> {
                        System.out.println("завершение программы");
                        return;
                    }
                    default -> System.out.println("неизвестная команда");

                }

            }catch (BookNotFoundException | NoAvailableCopiesException e) {
                System.out.println("ошибка: " + e.getMessage());
            }catch (InputMismatchException e) {
                System.out.println("ошибка ввода");
                scanner.nextLine();
            }
        }
    }

    public static void printActions(){
        System.out.print("1. Вывести каталог.\n" +
                "2. Добавить книгу.\n" +
                "3. Выдать книгу.\n" +
                "4. Вернуть книгу.\n" +
                "5. Выйти из приложения.\n");
    }

}
