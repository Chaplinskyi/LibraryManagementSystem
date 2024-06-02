package library.operations;

import library.books.Book;
import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter name of a book: ");
        String search = scanner.nextLine();
        String format = "|%1$-15s|%2$-15s|%3$-15s|%4$-15s|%5$-10s|%6$-10s|%7$-10s|\n";

        int indexOfBook = database.getBook(search);

        if(indexOfBook > -1) {
            Book book = database.getBookFromIndex(indexOfBook);
            System.out.println();
            System.out.format(format, "Title", "Author", "Publisher", "Address", "Q-ty", "Price", "Brw Cps");
            System.out.format(format, book.getTitle(), book.getAuthor(), book.getPublisher(),
                    book.getAddress(), book.getQuantity(), book.getPrice(), book.getBrwcopies());
        } else {
            System.out.println("Sorry, that book is not available");
        }
        user.menu(database, user);
    }
}
