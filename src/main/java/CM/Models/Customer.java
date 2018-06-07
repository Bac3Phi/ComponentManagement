package CM.Models;

public class Customer {
    String CustomerID;
    String CustomerName;
    String CustomerAddress;
    String CustomerEmail;
    String CustomerPhoneNo;

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String MaKH) {
        this.CustomerID = MaKH;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPhoneNo() {
        return CustomerPhoneNo;
    }

    public void setCustomerPhoneNo(String sodienthoai) {
        CustomerPhoneNo = sodienthoai;
    }


    public Customer(String khID, String customerName, String customerAddress, String customerEmail, String sodienthoai) {
        CustomerID = khID;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        CustomerEmail = customerEmail;
        CustomerPhoneNo = sodienthoai;
    }
}
