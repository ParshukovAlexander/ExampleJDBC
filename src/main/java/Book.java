import java.sql.*;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;
    private int price;

    public Book( int id,String title, String author, int quantity, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}' + '\n' ;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public void buy(int count) {
        if (count<=quantity) quantity=quantity-count;
        else throw new IllegalArgumentException("Некоректные данные");
    }
}
