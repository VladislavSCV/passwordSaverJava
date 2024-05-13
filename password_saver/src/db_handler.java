import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class db_handler extends db_config {
    Connection conn;
    public Connection getDB_connection() throws ClassNotFoundException, SQLException{
        String connStr = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(connStr, dbUser, dbPassw);

        return conn;
    }

    public void add_passw_to_db(String service, String passw) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + db_const.USER_TABLE +
                "(" + db_const.SERVICE + "," + db_const.PASSWORD + ")" +
                "VALUES (?, ?);";
        try {
            Connection connection = getDB_connection();
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setString(1, service);
            prSt.setString(2, passw);
            prSt.executeUpdate();
            prSt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void return_all_passw_fDB() {

    }

}