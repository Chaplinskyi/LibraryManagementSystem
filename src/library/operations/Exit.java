package library.operations;

import library.database.Database;
import library.form.Form;
import library.users.User;

import java.util.Scanner;

public class Exit implements IOOperation{

    Scanner scanner;
    Database database;
    User user;

    @Override
    public void operation(Database database, User user) {
        this.database = database;
        this.user = user;

        System.out.println("\nAre you sure you want to quit?\n" +
                "1. Yes\n2. Main Menu\n");
        scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equals("1")) {
            Form.mainMenu();
        } else {
            user.menu(database, user);
        }
    }
}
