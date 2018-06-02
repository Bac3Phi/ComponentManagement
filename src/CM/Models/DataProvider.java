package CM.Models;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProvider {
    static Connection myConn;
    static String myPath = "jdbc:mysql://localhost:3306/database-qllk?characterEncoding=utf8";
    //đây là đối tượng như Statement nhưng được cải thiện để nhanh hơn
    PreparedStatement myPrep = null;
    ResultSet myRs;
    Statement myStmt;

    //Khởi tạo connection
    public DataProvider() {
        try {
            //Tạo một connection tới dtb
            myConn = DriverManager.getConnection(myPath, "root", "");
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
    public int ExecuteSQLInsert(String[] strInsert, String tableName) {
        int rowInserted = 0;
        String param = "";
        int iLen = strInsert.length + (strInsert.length - 1);
        String[] str = new String[iLen];

        for (int i = 0; i < iLen; i++) {
            if (i % 2 == 0)
                str[i] = "?";
            else if (i % 2 != 0)
                str[i] = ",";
            param += str[i];
        }

        String query = "INSERT INTO " + tableName + " VALUES("+ param + ");";

        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            for (int i = 0; i < strInsert.length; i++) {
                myPrep.setString(i + 1, strInsert[i]);
            }

            myPrep.executeUpdate();
            rowInserted = 1;
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }
        return rowInserted;
    }

    //Hàm UPDATE
    public int ExecuteSQLUpdate(String[] colLabel, String[] strUpdate, String tableName) {
        int rowUpdated = 0;
        String res = "=?,";
        String param = "";

        for (int j = 1; j < colLabel.length; j++) {
            param += (colLabel[j] + res);
        }
        String output =  param.substring(0, param.length()-1);

        String query = "UPDATE " + tableName + " SET " + output + " WHERE " + colLabel[0] + " = ?";

        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            for (int i = 1; i <= strUpdate.length - 1; i++) {
                myPrep.setString(i, strUpdate[i]);
            }
            myPrep.setString(strUpdate.length, strUpdate[0]);

            myPrep.executeUpdate();
            rowUpdated = 1;
        }
        catch (SQLException e){
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, e);
        }

        return rowUpdated;
    }

    //Hàm DELETE
    public int ExecuteSQLDelete(String[] strDelete, String tableName, String col) {
        int rowDeleted = 0;
        String query = "DELETE FROM " + tableName + " WHERE " + col +" = ?";
        //đây là đối tượng như Statement nhưng được cải thiện để nhanh hơn
        PreparedStatement myPrep = null;

        try {
            //Tạo một connection tới dtb
            myPrep = myConn.prepareStatement(query);
            myPrep.setString(1, strDelete[0]);

            myPrep.executeUpdate();
            rowDeleted = 1;
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
