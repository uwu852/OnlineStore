
package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        List<Product> catalog = Arrays.asList(
                new Product(1, "Ноутбук", 19999.99,
                        "Ноутбук для роботи та ігор", electronics),

                new Product(2, "Смартфон", 12999.50,
                        "Смартфон з великим екраном", smartphones),

                new Product(3, "Навушники", 2499.00,
                        "Бездротові навушники", accessories)
        );

        Cart cart = new Cart();


        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\n=== ІНТЕРНЕТ-МАГАЗИН ===");
            System.out.println("1 - Переглянути товари");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Оформити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Історія замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");
            System.out.print("Оберіть дію: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Введіть число!");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\nКаталог товарів:");
                    for (Product product : catalog) {
                        printProduct(product);
                    }
                    break;

                case 2:
                    System.out.print("Введіть ID товару: ");
                    int addId = readInt(scanner);

                    Product selected = findById(catalog, addId);

                    if (selected != null) {
                        cart.addProduct(selected);
                        System.out.println("Товар додано до кошика!");
                    } else {
                        System.out.println("Товар не знайдено!");
                    }
                    break;

                case 3:
                    System.out.println(cart);
                    break;

                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній!");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);

                        System.out.println("Замовлення оформлено!");
                        System.out.println(order);

                        cart.clear();
                    }
                    break;

                case 5:
                    System.out.print("Введіть ID товару для видалення: ");
                    int removeId = readInt(scanner);

                    if (cart.removeProductById(removeId)) {
                        System.out.println("Товар видалено!");
                    } else {
                        System.out.println("Товару немає у кошику!");
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println(
                                    "\nЗамовлення №" + (i + 1)
                            );
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;

                case 7:
                    System.out.print(
                            "Введіть назву товару або категорію: "
                    );

                    String query = scanner.nextLine().toLowerCase();
                    boolean found = false;

                    for (Product product : catalog) {
                        if (product.getName().toLowerCase().contains(query)
                                || product.getCategory().getName()
                                .toLowerCase().contains(query)) {

                            printProduct(product);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Товарів не знайдено.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо за покупки!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невідома команда!");
            }
        }
    }

    // Пошук товару за ID
    private static Product findById(List<Product> catalog, int id) {
        for (Product product : catalog) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    private static int readInt(Scanner scanner) {
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        }

        scanner.nextLine();
        return -1;
    }

    // Виведення інформації про товар
    private static void printProduct(Product product) {
        System.out.printf(
                java.util.Locale.US,
                "%d - %s - %.2f грн | Категорія: %s%n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}
