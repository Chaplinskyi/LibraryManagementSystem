package library.books;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private String address; //Collection location
    private int quantity;   //Copies for sale
    private double price;
    private int brwcopies; //Copies for borrowing

    public Book(){}

    public Book(String title, String author, String publisher, String address, int quantity, double price, int brwcopies) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.quantity = quantity;
        this.price = price;
        this.brwcopies = brwcopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }

    public static Book parseBook(String str){
        String[] line = str.split(",");
        Book book = new Book();
        book.setTitle(line[0]);
        book.setAuthor(line[1]);
        book.setPublisher(line[2]);
        book.setAddress(line[3]);
        book.setQuantity(Integer.parseInt(line[4]));
        book.setPrice(Double.parseDouble(line[5]));
        book.setBrwcopies(Integer.parseInt(line[6]));
        return book;
    }

    @Override
    public String toString() {
        return getTitle() + "," + getAuthor() +
                "," + getPublisher() + "," + getAddress() +
                "," + getQuantity() + "," + getPrice() +
                "," + getBrwcopies();
    }
}
