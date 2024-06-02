package library.operations;

import library.books.Book;
import library.database.Database;
import library.books.orders.Order;
import library.users.User;

import java.util.Scanner;

public class PlaceOrder implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Order order = new Order();

        System.out.println("\nEnter book name for order: ");
        Scanner scanner = new Scanner(System.in);

        String bookName = scanner.nextLine();
        int indexOfBook = database.getBook(bookName);
        if (indexOfBook == -1) {
            System.out.println("Book not found\n");
        } else {
            Book book = database.getBookFromIndex(indexOfBook);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter quantity for order: ");
            int quantity = scanner.nextInt();
            if(book.getQuantity() < quantity) {
                System.out.println("There are not enough books" + " - " +
                        book.getTitle() + "\nAt moment we have only " + book.getQuantity() + " books.");
                user.menu(database, user);
            }
            order.setQuantity(quantity);
            order.setPrice(book.getPrice()*quantity);
            book.setQuantity(book.getQuantity()-quantity);
            database.addOrder(order, book, indexOfBook);
            System.out.println("Order placed successfully!\n");
        }
        user.menu(database, user);
    }
}
