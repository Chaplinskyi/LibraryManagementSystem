package library.operations;

import library.database.Database;
import library.users.User;

public interface IOOperation {
    void operation(Database database, User user);
}
