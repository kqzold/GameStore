package com.example;

import com.example.interfaces.Service;
import com.example.interfaces.Input;
import com.example.model.User;
import com.example.model.Game;
import com.example.model.Purchase;
import java.util.Scanner;

public class App implements Input {

    private final Service<Game> gameService;
    private final Service<User> userService;
    private final Service<Purchase> purchaseService;
    private final Scanner scanner = new Scanner(System.in);

    public App(Service<Game> gamService, Service<User> userService, Service<Purchase> purchaseService) {
        this.gameService = gamService;
        this.userService = userService;
        this.purchaseService = purchaseService;
    }

    public void run() {
        System.out.println("------ Магазин спортивного инвентаря группы NPTV23 ------");
        System.out.println("-------------------------------------------------------");

        boolean repeat = true;
        do {
            displayMenu();

            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(getInput());
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    System.out.println("----- Добавление продукта -----");
                    gameService.add();
                    break;
                case 2:
                    System.out.println("----- Список продуктов -----");
                    gameService.print();
                    break;
                case 3:
                    System.out.println("----- Редактирование продукта -----");
                    gameService.edit(null); // Assuming the service handles the selection and editing
                    break;
                case 4:
                    System.out.println("----- Удаление товара -----");
                    gameService.remove(null); // Assuming the service handles the selection and removal
                    break;
                case 5:
                    System.out.println("----- Добавление клиента -----");
                    userService.add();
                    break;
                case 6:
                    System.out.println("----- Список клиентов -----");
                    userService.print();
                    break;
                case 7:
                    System.out.println("----- Редактирование клиента -----");
                    userService.edit(null); // Assuming the service handles the selection and editing
                    break;
                case 8:
                    System.out.println("----- Удаление клиента -----");
                    userService.remove(null); // Assuming the service handles the selection and removal
                    break;
                case 9:
                    System.out.println("----- Покупка товара -----");
                    purchaseService.add();
                    break;
                case 10:
                    System.out.println("----- Список приобретенных товаров -----");
                    purchaseService.print();
                    break;
                default:
                    System.out.println("Выберите задачу из списка!");
            }
            System.out.println("-------------------------------------------------------");
        } while (repeat);

        System.out.println("До свидания :)");
    }

    private void displayMenu() {
        System.out.println("Список задач: ");
        System.out.println("0. Выйти из программы");
        System.out.println("1. Добавить продукт");
        System.out.println("2. Список продуктов");
        System.out.println("3. Редактировать продукт");
        System.out.println("4. Удалить товар");
        System.out.println("5. Добавить клиента");
        System.out.println("6. Список клиентов");
        System.out.println("7. Редактировать клиента");
        System.out.println("8. Удалить клиента");
        System.out.println("9. Купить товар");
        System.out.println("10. Список приобретенных товаров");
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}