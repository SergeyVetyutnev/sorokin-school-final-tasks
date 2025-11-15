package dev.sergeyvet.exeptions.module.task;

import dev.sergeyvet.exeptions.module.task.exceptions.BookNotFoundException;
import dev.sergeyvet.exeptions.module.task.exceptions.NoAvailableCopiesException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Library library = new Library();

        //заполнение библиотеки книгами
        for (int i = 0; i < 10; i++) {
            library.addBook("title" + i,
                    "author" + i,
                    random.nextInt(0,3));
        }


        try (Scanner scanner = new Scanner(System.in)) {
            while (true){
                try {
                    printActions();
                    int userChose = readIntegerInput(scanner, "введите номер действия: ");

                    switch (userChose){
                        case 1 -> library.printBooks();
                        case 2 -> {
                            System.out.print("введите название книги: ");
                            String title = scanner.nextLine();

                            System.out.print("введите автора: ");
                            String author = scanner.nextLine();

                            int copies = readIntegerInput(scanner, "введите кол-во копий: ");

                            library.addBook(title, author, copies);
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

                }catch (BookNotFoundException | NoAvailableCopiesException | IllegalArgumentException e) {
                    System.out.println("ошибка: " + e.getMessage());
                }
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

    public static int readIntegerInput(Scanner scanner, String promt){
        while (true){
            try {
                System.out.print(promt);
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("ошибка: ввод должен быть целым числом, попробуйте еще раз");
                scanner.nextLine();
            }
        }
    }

}
