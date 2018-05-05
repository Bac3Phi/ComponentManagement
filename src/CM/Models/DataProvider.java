package CM.Models;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProvider {
    static Connection myConn;
    static String myPath = "jdbc:mysql://localhost:3306/database-qllk";
    //đây là đối tượng như Statement nhưng được cải thiện để nhanh hơn
    PreparedStatement myPrep = null;
    ResultSet myRs;
    Statement myStmt;

    //Khởi tạo connection
    public DataProvider() {
        try {
            //Tạo một connection tới dtb
            myConn = DriverManager.getConnection(myPath, "root", "YES");
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Hàm lấy dữ liệu từ bảng trong database
    public ResultSet getData(String str) throws SQLException{
        //Tạo một statement để kết nối tới CSDL
        myStmt = myConn.createStatement();

        //Thực hiện câu lệnh SQL
        myRs = myStmt.executeQuery(str);   //myRs chứa kết quả từ CSDL

        return myRs;
    }

    //Hàm INSERT
    public int ExecuteSQLInsert(String[] strInsert, String query) {
        int rowInserted = 0;
//        String query = "INSERT INTO KHACHHANG(KHID, TENKH, DIACHI, EMAIL, SODIENTHOAI) VALUES(?,?,?,?,?)";

        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            myPrep.setString(1, strInsert[0]);
            myPrep.setString(2, strInsert[1]);
            myPrep.setString(3, strInsert[2]);
            myPrep.setString(4, strInsert[3]);
            myPrep.setString(5, strInsert[4]);

            rowInserted = myPrep.executeUpdate(query);
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }
        return rowInserted;
    }

    //Hàm UPDATE
    public int ExecuteSQLUpdate(String[] strUpdate, String query) {
        int rowUpdated = 0;
        //String query = "UPDATE KHACHHANG SET KHID = ?, TENKH = ?, DIACHI = ?, EMAIL = ?, SODIENTHOAI = ? WHERE KHID = ?";


        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            myPrep.setString(1, strUpdate[0]);
            myPrep.setString(2, strUpdate[1]);
            myPrep.setString(3, strUpdate[2]);
            myPrep.setString(4, strUpdate[3]);
            myPrep.setString(5, strUpdate[4]);

            rowUpdated = myPrep.executeUpdate(query);
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }

        return rowUpdated;
    }

    //Hàm DELETE
    public int ExecuteSQLDelete(String[] strDelete, String query) {
        int rowDeleted = 0;
        //String query = "DELETE FROM KHACHHANG WHERE KHID = ?";
        //đây là đối tượng như Statement nhưng được cải thiện để nhanh hơn
        PreparedStatement myPrep = null;

        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            myPrep.setString(1, strDelete[0]);

            rowDeleted = myPrep.executeUpdate(query);
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }

        return rowDeleted;
    }

    //Hàm close connection
    public void close() throws SQLException{
        myStmt.close();
        myRs.close();
    }
}
