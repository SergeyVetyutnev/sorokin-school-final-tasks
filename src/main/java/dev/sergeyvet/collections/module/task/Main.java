package dev.sergeyvet.collections.module.task;

import dev.sergeyvet.collections.module.task.exceptions.ContactAlreadyExistException;
import dev.sergeyvet.collections.module.task.exceptions.ContactNotFoundException;
import dev.sergeyvet.collections.module.task.exceptions.InvalidContactDataException;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        Random random = new Random();

        for (int i = 0; i <5; i++) {
            contactBook.addContact("name" + i,
                    "+phone" + i,
                    "email@" + i,
                    "group" + i);
        }

        try (Scanner scanner = new Scanner(System.in)){
            printActions();
            while (true){
                try {
                    int userChose = readIntegerInput(scanner, "введите номер действия: ");

                    switch (userChose){
                        case 0 -> {
                            System.out.println("завершение программы");
                            return;
                        }
                        case 1 -> {
                            System.out.print("введите имя: ");
                            String name = scanner.nextLine();
                            System.out.print("введите номер: ");
                            String phone = scanner.nextLine();
                            System.out.print("введите почту: ");
                            String email = scanner.nextLine();
                            System.out.print("введите группу: ");
                            String group = scanner.nextLine();

                            contactBook.addContact(name, phone, email, group);
                        }
                        case 2 -> {
                            System.out.print("введите номер: ");
                            String phone = scanner.nextLine();
                            contactBook.deleteContact(phone);
                        }
                        case 3 -> {
                            contactBook.printContacts();
                        }
                        case 4 -> {
                            int searchVariant = readIntegerInput(scanner,
                                    "выберите номер, по которому будете искать (1-имя 2-номер 3-почта): ");
                            System.out.print("введите поисковой запрос: ");
                            String searchString = scanner.nextLine();
                            switch (searchVariant){
                                case 1 -> System.out.println(contactBook.findByName(searchString));
                                case 2 -> System.out.println(contactBook.findByPhone(searchString));
                                case 3 -> System.out.println(contactBook.findByEmail(searchString));
                                default -> System.out.println("неизвестный номер, возможны только 1-3");
                            }
                        }
                        case 5 -> {
                            System.out.print("введите группу: ");
                            String group = scanner.nextLine();
                            contactBook.printContactsByGroup(group);
                        }
                        case 6 -> {
                            printActions();
                        }
                        default -> System.out.println("неизвестное действие");
                    }
                }catch (ContactAlreadyExistException | InvalidContactDataException | ContactNotFoundException e){
                    System.out.println("ошибка: " + e.getMessage());
                }
            }
        }

    }

    private static void printActions(){
        System.out.println("«1»: Добавить контакт\n" +
                "«2»: Удалить контакт\n" +
                "«3»: Посмотреть все контакты\n" +
                "«4»: Найти контакт\n" +
                "«5»: Посмотреть контакты по группе\n" +
                "«6»: Посмотреть действия\n" +
                "«0»: Выход");
    }

    private static int readIntegerInput(Scanner scanner, String promt){
        while (true){
            try {
                System.out.print(promt);
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            }catch (InputMismatchException e){
                System.out.println("ошибка: ввод должен быть целым числом, попробуйте еще раз");
                scanner.nextLine();
            }
        }
    }

}
