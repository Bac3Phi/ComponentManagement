package CM.Functions;

import CM.Models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerFunction {
    Connection conn = null;
    PreparedStatement ptmt = null;
    Scanner scanner = new Scanner(System.in);
    public void insertCustomer(Customer customer)
    {
        DbConnection Db= new DbConnection();
        conn = Db.connect();

        String sql = "INSERT into Khachhang(Khid,Tenkh,diachi,Email,Sodienthoai) values(?,?,?,?,?)";

        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,customer.getCustomerID());
            ptmt.setString(2,customer.getCustomerName());
            ptmt.setString(3,customer.getCustomerAddress());
            ptmt.setString(4,customer.getCustomerEmail());
            ptmt.setString(5,customer.getCustomerPhoneNo());

            int checkUpdate = ptmt.executeUpdate();
            if (checkUpdate!=0)
            {
                System.out.println("Thêm Thành Công!!!");
            }
            else System.out.println("Thất bại rồi");
            ptmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
