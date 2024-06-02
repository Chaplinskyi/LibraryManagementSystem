package library.operations;

import library.books.borrowing.Borrowing;
import library.database.Database;
import library.users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculateFine implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        ArrayList<Borrowing> borrowings = database.getAllBorrowings();
        boolean isTrue = true;

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose one option: \n" +
                "1. View all borrowings\n2. View borrowing by book\n");

        int choice = scanner.nextInt();

        if (choice == 1) {
            if(borrowings.isEmpty()){
                System.out.println("\nThe list of borrowings is empty");
            } else {
                String format = "|%1$-15s|%2$-15s|%3$-10s|\n";

                System.out.println();
                System.out.format(format, "Book", "Username", "Days Left");
                for(Borrowing borrowing : borrowings){
                    System.out.format(format, borrowing.getBook().getTitle(), borrowing.getUser().getName(), borrowing.getDaysLeft() < 0 ? "Overdue" : borrowing.getDaysLeft());
                }
            }
        } else {
            scanner = new Scanner(System.in);
            System.out.println("\nEnter the book title: ");
            String bookTitle = scanner.nextLine();

            for (Borrowing borrowing : database.getAllBorrowings()) {
                if (borrowing.getBook().getTitle().matches(bookTitle) &&
                        borrowing.getUser().getName().matches(user.getName())) {
                    if (borrowing.getDaysLeft() < 0) {
                        System.out.println("\nYou are late!\n"
                                + "You have to pay " + borrowing.getDaysLeft() * 50 + " as fine!");
                    } else {
                        System.out.println("\nYou don't have to pay!");
                        System.out.println(borrowing.getDaysLeft() + " days left");
                    }
                    isTrue = false;
                }
            }
            if(isTrue) {
                System.out.println("\nYou didn't borrow this book!");
            }
        }
        user.menu(database, user);
    }
}
