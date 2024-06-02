package library.users;

import library.database.Database;
import library.operations.IOOperation;

public abstract class User {

    protected String name;
    protected String email;
    protected String phoneNumber;
    protected IOOperation[] operations;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    abstract public void menu(Database database, User user);

    @Override
    public String toString() {
        return getName() + "," + getEmail() +
                "," + getPhoneNumber();
    }
}
