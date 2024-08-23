import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLConnect {

    // DB Connection variables

    static Connection connection = null;
    String databaseName = "";
    static String url = "jdbc:mysql://localhost:3306/ducat2_30pm";

    static String username = "root";
    static String password = "Harshit@1936";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, username, password);
        PreparedStatement ps = connection.prepareStatement("INSERT INTO 'studentdatabase'.'student' ('name') VALUES ('michael clark');");

        int status = ps.executeUpdate();

        if (status != 0) {
            System.out.println("Database was Connected");
            System.out.println("Record was INSERTED");
        }

    }
}
