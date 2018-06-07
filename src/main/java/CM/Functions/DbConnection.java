package CM.Functions;

import java.sql.*;

public class DbConnection {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/qllk";
    String username = "root";
    String password = "";

    public Connection connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
           /* if (conn!=null)
                System.out.println("Kết nối thành công");*/
        } catch (ClassNotFoundException e) {
            System.out.println(e); e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return conn;
    }
}
