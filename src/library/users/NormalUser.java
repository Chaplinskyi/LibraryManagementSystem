package library.users;

import library.database.Database;
import library.operations.*;

import java.util.Scanner;

public class NormalUser extends User {

    public NormalUser(String name) {
        super(name);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public NormalUser(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("\nWelcome, " + user.getName() + "!");
        System.out.println("\n1. View Books\n2. Search\n3. Place Order\n4. Borrow Book\n5. Calculate Fine\n6. Return Book\n7. Exit\n");
        Scanner scanner = new Scanner(System.in);

        String choice = scanner.next();
        if(choice.matches("[1-7]")){
            this.operations[Integer.parseInt(choice) - 1].operation(database, user);
            scanner.close();
        } else {
            System.out.println("\nWrong choice. Try again.");
            menu(database, user);
        }
    }

    @Override
    public String toString() {
        return getName() + "," + getEmail() +
                "," + getPhoneNumber() + "," + "User";
    }

}
