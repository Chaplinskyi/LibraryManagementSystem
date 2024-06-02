package library.operations;

import library.books.Book;
import library.books.borrowing.Borrowing;
import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class ReturnBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the book title: ");
        String bookTitle = scanner.nextLine();

        if(!database.getAllBorrowings().isEmpty()) {
            for(Borrowing borrowing : database.getAllBorrowings()) {
                if(borrowing.getBook().getTitle().matches(bookTitle) &&
                        borrowing.getUser().getName().matches(user.getName())) {
                    Book book = borrowing.getBook();
                    int bookIndex = database.getAllBooks().indexOf(book);
                    if(borrowing.getDaysLeft() < 0) {
                        System.out.println("You are late!\n"
                                + "You have to pay " + borrowing.getDaysLeft() * 50 + " as fine!");
                    }
                    book.setBrwcopies(book.getBrwcopies() + 1);
                    database.returnBook(borrowing, book, bookIndex);
                    System.out.println("Book returned!\nThank you!");
                    break;
                } else {
                    System.out.println("\nYou didn't borrow this book!");
                }
            }
        } else {
            System.out.println("\nYou didn't borrow this book!");
        }
        user.menu(database, user);
    }
}
