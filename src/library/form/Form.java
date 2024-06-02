package library.form;

import library.database.Database;
import library.users.Admin;
import library.users.NormalUser;
import library.users.User;

import java.util.Scanner;

public class Form {

    static Database database = new Database();
    static Scanner scanner = new Scanner(System.in);;

    public static void mainMenu() {
        System.out.println("\n1. Login\n2. Register\n3. Exit\n");
        try{
            String choice = scanner.next();
            switch (Integer.parseInt(choice)) {
                case 1: Form.login(); break;
                case 2: Form.newUser(); break;
                case 3: System.exit(0); break;
                default: System.out.println("\nWrong choice. Try again."); mainMenu();
            }
        } catch(NumberFormatException nfe) {
            System.out.println("\nEnter numbers only. Try again.");
            mainMenu();
        }
        scanner.close();
    }

    public static void login() {
        System.out.println("\nPlease enter your phone number: ");
        String phoneNumber = scanner.next();
        System.out.println("Please enter your e-mail: ");
        String email = scanner.next();
        int indexOfUser = database.login(phoneNumber, email);
        if(indexOfUser != -1) {
            User user = database.getUser(indexOfUser);
            user.menu(database, user);
        } else {
            System.out.println("\nUser does not exist!\n");
            mainMenu();
        }
        scanner.close();
    }

    public static void newUser() {
        System.out.println("\nPlease enter your name: ");
        String name = scanner.next();
        if(database.userExists(name)) {
            System.out.println("User name already exists!");
            newUser();
        }
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.next();
        if(database.userNumberExists(phoneNumber)) {
            System.out.println("Number already exists!");
            newUser();
        }
        System.out.println("Please enter your e-mail: ");
        String email = scanner.next();
        if(database.userEmailExists(email)) {
            System.out.println("E-mail already exists!");
            newUser();
        }
        System.out.println("1.Admin\n2.User");
        int role = scanner.nextInt();
        User user;
        if (role == 1) {
            user = new Admin(name, email, phoneNumber);
        } else {
            user = new NormalUser(name, email, phoneNumber);
        }
        System.out.println("Account created successfully!");
        database.addUser(user);
        user.menu(database, user);
        scanner.close();
    }
}
