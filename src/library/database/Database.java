package library.database;

import library.books.Book;
import library.books.borrowing.Borrowing;
import library.books.orders.Order;
import library.users.Admin;
import library.users.NormalUser;
import library.users.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static library.books.Book.parseBook;

public class Database {

    //DATABASES
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<String> bookNames = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Borrowing> borrowings = new ArrayList<>();

    //FILES
    private File folder = new File("data");
    private File usersFile = new File("data/users.txt");
    private File booksFile = new File("data/books.txt");
    private File ordersFile = new File("data/orders.txt");
    private File borrowingsFile = new File("data/borrowings.txt");

    public Database() {
        //CHECKING IF FILES FOR SAVING EXIST
        if(!folder.exists()) {
            try{
                folder.mkdir();
            } catch (Exception e){
                System.err.println("Error creating folder");
            }
        }
        if(!usersFile.exists()) {
            try{
                usersFile.createNewFile();
            } catch (Exception e){
                System.err.println("Error creating users file");
            }
        }
        if(!booksFile.exists()) {
            try{
                booksFile.createNewFile();
            } catch (Exception e){
                System.err.println("Error creating books file");
            }
        }
        if(!ordersFile.exists()) {
            try{
                ordersFile.createNewFile();
            } catch (Exception e){
                System.err.println("Error creating books file");
            }
        }
        if(!borrowingsFile.exists()) {
            try{
                borrowingsFile.createNewFile();
            } catch (Exception e){
                System.err.println("Error creating books file");
            }
        }
        getUsers();
        getBooks();
        getOrders();
        getBorrowings();
    }

    // ---------------------------------------USERS-------------------------------------

    //GETTING USER INDEX FROM DATABASE
    public int login(String phoneNumber, String email) {
        int result = -1;

        for (User user : users) {
            if (user.getPhoneNumber().matches(phoneNumber) && user.getEmail().matches(email)) {
                result = users.indexOf(user);
                break;
            }
        }
        return result;
    }

    //ADDING USER INTO DATABASE
    public void addUser(User user) {
        users.add(user);
        userNames.add(user.getName());
        saveUsers();
    }

    //GETTING USER FROM DATABASE
    public User getUser(int indexOfUser) {
        return users.get(indexOfUser);
    }

    private void getUsers() {
        String text = "";

        //GETTING USERS FROM FILE DATABASE - users.txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersFile));
            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        //ADDING USER FROM FILE DB to DB.java
        if(!text.isEmpty() || !text.matches("")) {
            String[] usersString = text.split("/");
            for(String string : usersString) {
                String[] userString = string.split(",");
                if(userString[3].matches("Admin")) {
                    User user = new Admin(userString[0], userString[1], userString[2]);
                    addUser(user);
                } else {
                    User user = new NormalUser(userString[0], userString[1], userString[2]);
                    addUser(user);
                }
            }
        }
    }

    public User getUserByName(String name) {
        User result = new NormalUser("");
        for(User user : users) {
            if(user.getName().matches(name)) {
                result =  user;
                break;
            }
        }
        return result;
    }

    private void saveUsers() {
        String text = "";
        for (User user : users) {
            text += user.toString() + "/\n";
        }
        try {
            FileWriter writer = new FileWriter(usersFile);
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean userExists(String name) {
        boolean result = false;

        for(User user : users) {
            if(user.getName().matches(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean userNumberExists(String number) {
        boolean result = false;

        for(User user : users) {
            if(user.getPhoneNumber().matches(number)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean userEmailExists(String email) {
        boolean result = false;

        for(User user : users) {
            if(user.getEmail().matches(email)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // ---------------------------------------BOOKS-------------------------------------

    //ADDING BOOK INTO DATABASE
    public void addBook(Book book) {
        books.add(book);
        bookNames.add(book.getTitle());
        saveBooks();
    }

    public int getBook(String bookName) {
        int index = -1;
        for(Book book : books) {
            if(book.getTitle().matches(bookName)) {
                index = books.indexOf(book);
            }
        }
        return index;
    }

    public Book getBookFromIndex(int bookIndex) {
        return books.get(bookIndex);
    }

    //GETTING BOOKS FROM FILE DB
    private void getBooks() {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(booksFile));
            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        if(!text.isEmpty() || !text.matches("")) {
            String[] booksString = text.split("/");
            for(String string : booksString) {
                Book book = parseBook(string);
                addBook(book);
            }
        }
    }

    private void saveBooks() {
        String text = "";
        for (Book book : books) {
            text += book.toString() + "/\n";
        }
        try {
            FileWriter writer = new FileWriter(booksFile);
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void deleteBook(int index) {
        books.remove(index);
        bookNames.remove(index);
        saveBooks();
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void deleteAllData() {
        if(usersFile.exists()) {
            try{
                usersFile.delete();
                System.out.println("File users.txt deleted successfully!");
            } catch (Exception e){
                System.err.println("Error! File is not found!");
            }
        }
        if(booksFile.exists()) {
            try{
                booksFile.delete();
                System.out.println("File books.txt deleted successfully!");
            } catch (Exception e){
                System.err.println("Error! File is not found!");
            }
        }
        if(ordersFile.exists()) {
            try{
                ordersFile.delete();
                System.out.println("File orders.txt deleted successfully!");
            } catch (Exception e){
                System.err.println("Error! File is not found!");
            }
        }
        if(borrowingsFile.exists()) {
            try{
                borrowingsFile.delete();
                System.out.println("File borrowings.txt deleted successfully!");
            } catch (Exception e){
                System.err.println("Error! File is not found!");
            }
        }
        if(folder.exists()) {
            try{
                folder.delete();
            } catch (Exception e){
                System.err.println("Error! Folder not found!");
            }
        }
    }

    // ---------------------------------------ORDERS-------------------------------------

    public void addOrder(Order order) {
        orders.add(order);
        saveOrders();
        saveBooks();
    }

    public void addOrder(Order order, Book book, int bookIndex) {
        orders.add(order);
        books.set(bookIndex, book);
        saveOrders();
        saveBooks();
    }

    private void saveOrders() {
        String text = "";
        for (Order order : orders) {
            text += order.toString() + "/\n";
        }
        try {
            FileWriter writer = new FileWriter(ordersFile);
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void getOrders() {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ordersFile));
            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        if(!text.isEmpty() || !text.matches("")) {
            String[] ordersString = text.split("/");
            for(String string : ordersString) {
                String[] line = string.split(",");
                Order order = new Order(books.get(getBook(line[0])), getUserByName(line[1]),
                        Double.parseDouble(line[2]), Integer.parseInt(line[3]));
                addOrder(order);
            }
        }
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    // ---------------------------------------BORROWING-------------------------------------

    public void addBorrowing(Borrowing borrowing) {
        borrowings.add(borrowing);
        saveBorrowings();
        saveBooks();
    }

    private void getBorrowings() {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(borrowingsFile));
            String line;
            while((line = reader.readLine()) != null) {
                text += line;
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e);
        }

        if(!text.isEmpty() || !text.matches("")) {
            String[] ordersString = text.split("//");
            for(String string : ordersString) {
                String[] line = string.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate start = LocalDate.parse(line[0], formatter);
                LocalDate finish = LocalDate.parse(line[1], formatter);
                Book book = getBookFromIndex(getBook(line[3]));
                User user = getUserByName(line[4]);
                Borrowing borrowing = new Borrowing(start, finish, book, user);
                addBorrowing(borrowing);
            }
        }
    }

    private void saveBorrowings() {
        String text = "";
        for (Borrowing borrowing : borrowings) {
            text += borrowing.toString() + "//\n";
        }
        try {
            FileWriter writer = new FileWriter(borrowingsFile);
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void borrowBook(Borrowing borrowing, Book book, int bookIndex) {
        borrowings.add(borrowing);
        books.set(bookIndex, book);
        saveBorrowings();
        saveBooks();
    }

    public ArrayList<Borrowing> getAllBorrowings() {
        return borrowings;
    }

    // ---------------------------------------BOOK_RETURNING-------------------------------------

    public void returnBook(Borrowing borrowing, Book book, int bookIndex) {
        borrowings.remove(borrowing);
        books.set(bookIndex, book);
        saveBorrowings();
        saveBooks();
    }
}
