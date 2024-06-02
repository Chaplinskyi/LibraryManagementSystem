package library.books.borrowing;

import library.books.Book;
import library.users.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Borrowing {
    LocalDate start, finish;
    int daysLeft;
    Book book;
    User user;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public Borrowing(Book book, User user) {
        start = LocalDate.now();
        finish = start.plusDays(14);
        daysLeft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public Borrowing(LocalDate start, LocalDate finish, Book book, User user) {
        this.start = start;
        this.finish = finish;
        this.daysLeft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public String getStart() {
        return formatter.format(start);
    }

    public String getFinish() {
        return formatter.format(finish);
    }

    public int getDaysLeft() {
        return Period.between(start, finish).getDays();
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

    @Override
    public String toString() {
        return getStart() + "," + getFinish() + "," + getDaysLeft() + "," + getBook().getTitle() + "," + getUser().getName();
    }
}
