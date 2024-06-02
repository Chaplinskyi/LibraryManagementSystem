package library.operations;

import library.books.Book;
import library.database.Database;
import library.users.User;

import java.util.ArrayList;

public class ViewBooks implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        ArrayList<Book> books = database.getAllBooks();

        String format = "|%1$-15s|%2$-15s|%3$-15s|%4$-15s|%5$-10s|%6$-10s|%7$-10s|\n";
        System.out.println();
        System.out.format(format, "Title", "Author", "Publisher", "Address", "Q-ty", "Price", "Brw Cps");

        for(Book book : books) {
            System.out.format(format, book.getTitle(), book.getAuthor(), book.getPublisher(),
                    book.getAddress(), book.getQuantity(), book.getPrice(), book.getBrwcopies());
        }
        user.menu(database, user);
    }
}
