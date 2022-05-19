import java.sql.*;

public abstract class BaseRepository {
    protected Connection connection;

    public BaseRepository() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_book",
                    "postgres", "12345");
        } catch (SQLException e) {
            System.out.println("error");
        }
    }
}
