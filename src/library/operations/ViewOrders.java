package library.operations;

import library.database.Database;
import library.books.orders.Order;
import library.users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        ArrayList<Order> orders = database.getAllOrders();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose one option: \n" +
                "1. View all orders\n2. View orders by book");

        int choice = scanner.nextInt();
        if (choice == 1) {
            String format = "|%1$-15s|%2$-15s|%3$-10s|%4$-10s|\n";
            System.out.println();
            System.out.format(format, "Book", "Username", "Price", "Q-ty");
            for(Order order : orders){
                System.out.format(format, order.getBook().getTitle(), order.getUser().getName(), order.getPrice(), order.getQuantity());
            }
        } else {
            System.out.println("\nEnter the book title: ");
            String bookTitle = scanner.nextLine();

            int bookIndex = database.getBook(bookTitle);

            if(bookIndex == -1){
                System.out.println("That book is not available\n");
            } else {
                for(Order order : orders){
                    if(order.getBook().getTitle().matches(bookTitle)){
                        String format = "|%1$-15s|%2$-15s|%3$-10s|%4$-10s|\n";
                        System.out.println();
                        System.out.format(format, "Book", "Username", "Price", "Q-ty");
                        System.out.format(format, order.getBook().getTitle(), order.getUser().getName(), order.getPrice(), order.getQuantity());
                    }
                }
            }
        }
        user.menu(database, user);
    }
}
