package library.books.orders;

import library.books.Book;
import library.users.User;

public class Order {
    private Book book;
    private User user;
    private double price;
    private int quantity;

    public Order() {}

    public Order(Book book, User user, double price, int quantity) {
        this.book = book;
        this.user = user;
        this.price = price;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return getBook().getTitle() + "," + getUser().getName() + "," + getPrice() + "," + getQuantity();
    }
}
