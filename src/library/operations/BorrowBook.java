package library.operations;

import library.books.Book;
import library.books.borrowing.Borrowing;
import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class BorrowBook implements IOOperation{
    @Override
    public void operation(Database database, User user) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the book title: ");
        String bookTitle = scanner.nextLine();

        int bookIndex = database.getBook(bookTitle);

        if(bookIndex == -1){
            System.out.println("\nThat book is not available!");
        } else {
            Book book = database.getBookFromIndex(bookIndex);

            boolean isNotBorrowed = true;

            for(Borrowing borrowing : database.getAllBorrowings()) {
                if(borrowing.getBook().getTitle().matches(bookTitle) &&
                        borrowing.getUser().getName().matches(user.getName())){
                    isNotBorrowed = false;
                    System.out.println("\nYou have borrowed this book before!");
                }
            }

            if(isNotBorrowed) {
                if(book.getBrwcopies() > 0) {
                    Borrowing borrowing = new Borrowing(book, user);
                    book.setBrwcopies(book.getBrwcopies() - 1);
                    database.borrowBook(borrowing, book, bookIndex);
                    System.out.println("You must return the book before 14 days from now\n" +
                            "Expire date: " + borrowing.getFinish() + "\nEnjoy!");
                }
            } else {
                System.out.println("\nThis book is not available for borrowing");
            }
        }
        user.menu(database, user);
    }
}
