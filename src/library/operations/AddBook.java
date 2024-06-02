package library.operations;

import library.books.Book;
import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void operation(Database database, User user) {

        Book book = new Book();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter book title: ");
        String title = scanner.nextLine();
        if (database.getBook(title) > -1) {
            System.out.println("Book already exists!\n");
            user.menu(database, user);
        } else {
            book.setTitle(title);
        }
        System.out.println("Enter book author: ");
        book.setAuthor(scanner.nextLine());
        System.out.println("Enter book publisher: ");
        book.setPublisher(scanner.nextLine());
        System.out.println("Enter book collection address: ");
        book.setAddress(scanner.nextLine());
        System.out.println("Enter quantity: ");
        book.setQuantity(scanner.nextInt());
        System.out.println("Enter price: ");
        book.setPrice(scanner.nextDouble());
        System.out.println("Enter borrowing copies: ");
        book.setBrwcopies(scanner.nextInt());
        database.addBook(book);
        System.out.println("Book added successfully!\n");
        user.menu(database, user);
        scanner.close();
    }
}
