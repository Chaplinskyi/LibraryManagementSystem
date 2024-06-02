package library.users;

import library.database.Database;
import library.operations.*;

import java.util.Scanner;

public class Admin extends User {

    public Admin(String name) {
        super(name);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    public Admin(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("\nWelcome, " + user.getName() + "!");
        System.out.println("\n1. View Books\n2. Add Book\n3. Delete book\n4. Search\n5. Delete All Data\n6. View Orders\n7. Exit\n");
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
                "," + getPhoneNumber() + "," + "Admin";
    }

}
