package library.operations;

import library.database.Database;
import library.users.User;

import java.util.Scanner;

public class DeleteAllData implements IOOperation{
    @Override
    public void operation(Database database, User user) {
        System.out.println("Are you sure you want to delete all the data?\n" +
                "1. Yes\n2. Main Menu");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equals("1")) {
            database.deleteAllData();
        } else {
            user.menu(database, user);
        }
    }
}
