package library.operations;

import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class DeleteBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the book title: ");
        String bookTitle = scanner.nextLine();

        int bookIndex = database.getBook(bookTitle);

        if(bookIndex == -1){
            System.out.println("That book is not available\n");
        } else {
            database.deleteBook(bookIndex);
            System.out.println("That book deleted successfully!\n");
        }
        user.menu(database, user);
    }
}
