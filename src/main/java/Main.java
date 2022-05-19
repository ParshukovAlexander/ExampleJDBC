import dnl.utils.text.table.TextTable;
import io.bretty.console.table.Table;

import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        System.out.println("Добро пожаловать в библиотеку книг");
//      getAllBook(bookRepository);
//     bookRepository.deleteForId(7);
//       getAllBook(bookRepository);
        int action ;
        do  {
            System.out.println("1.Посмотреть все книги в библиотеке");
            System.out.println("2.Добавить книгу");
            System.out.println("3.Найти книги автора");
            System.out.println("4.Продать");
            System.out.println("5.Удалить дубликаты");
            System.out.println("6.Найти книгу по названию");
            System.out.println("7.Показать всех авторов");
            System.out.println("8.Выйти");
            action =scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1 -> getAllBook(bookRepository.doGet());
                case 2 -> {
                    bookRepository.addBook(createBook());
                    System.out.println("Книга добавлена в каталог!");
                    getAllBook(bookRepository.doGet());
                }
                case 3 -> {
                    System.out.println("Введите имя автора");
                  getAllBook(bookRepository.searchByAuthor(scanner.nextLine()));
                }
                case 4 -> {
                    System.out.println("Выберите номер книги из списка:");
                    ArrayList<Book>books=bookRepository.doGet();
                    getAllBook(books);
                    Book book=books.get(scanner.nextInt());
                    System.out.println("Введите количство книг");
                    book.buy(scanner.nextInt());
                    bookRepository.updateBook(book);
                }
                case 5 -> {
                    bookRepository.deleteDuplicate();
                    System.out.println("Удаление дубликатов прошло успешно");
                    getAllBook(bookRepository.doGet());

                }
                case 6 -> {
                    System.out.println("Введите название книги");
                    bookRepository.searchByTitle(scanner.nextLine());
                }
                case 7 -> {
                    bookRepository.getAllAuthor();
                }
                case 8 -> {
                    break;
                }
                default -> System.out.println("Данный запрос неккоректен");
            }
        }while (action!=8);

    }

    private static void getAllBook(List<Book> bookList) {
        String[] title = {"Номер", "title", "author", "quantity", "price"};
        String[][] matrix = new String[bookList.size()][title.length];
        int count = 0;
        for (var b : bookList) {
            matrix[count][0] = String.valueOf(count);
            matrix[count][1] = b.getTitle();
            matrix[count][2] = b.getAuthor();
            matrix[count][3] = String.valueOf(b.getQuantity());
            matrix[count][4] = String.valueOf(b.getPrice());
            count++;
        }
        TextTable tt = new TextTable(title, matrix);
        tt.printTable();
    }

    public static Book createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("title: ");
        String title = scanner.next();
        System.out.println("author: ");
        String author = scanner.next();
        System.out.println("quantity: ");
        int quantity = scanner.nextInt();
        System.out.println("price: ");
        int price = scanner.nextInt();
        return new Book(0, title, author, quantity, price);
    }

}
