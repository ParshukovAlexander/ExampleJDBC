import java.sql.*;
import java.util.ArrayList;

public class BookRepository extends BaseRepository {
    public void addBook(Book book) {
        try {
            String sql = "INSERT INTO book ( title,author,quantity,price) Values (?, ?, ?, ?) returning id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setInt(4, book.getPrice());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            book.setId(id);
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public ArrayList<Book> doGet() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from book");
            while (rs.next()) {
                Book book = new Book(Integer.parseInt(rs.getString(1)), rs.getString(2),
                        rs.getString(3),
                        Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
        return books;
    }


    public void updateBook(Book book) {
        try {
            String sql = "update book set title = ? , author = ? ,quantity =?, price=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());
            preparedStatement.setInt(4, book.getPrice());
            preparedStatement.setInt(5, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Book> searchByAuthor(String nextLine) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String sql = "select * from book where author = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nextLine);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book(Integer.parseInt(rs.getString(1)), rs.getString(2),
                        rs.getString(3),
                        Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
        return books;
    }

    public void deleteDuplicate() {
        try {
            String sql = "DELETE FROM book WHERE ctid NOT IN(SELECT max(ctid) FROM book GROUP BY book.author " +
                    ", book.title, book.quantity, book.price)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void getAllAuthor() {
        try {
            String sql = "select distinct author from  book";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) System.out.println(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByTitle(String nextLine) {
        try {
            String sql = "select * from book where title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nextLine);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(" найдены следующие произведения:");
            while (resultSet.next()) {
                System.out.println("Назваие: " + resultSet.getString(2) + " Автор: " + resultSet.getString(3) + " количетсво: "
                        + resultSet.getString(4) + "  Цена: "
                        + resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("error");
        }
    }
}
