package CM.Models;

public class Suppliers {
    private String SupplierID;
    private String SupplierName;
    private String SupplierAddress;
    private String SupplierEmail;
    private String SupplierPhoneNo;

    public Suppliers() {}

    public Suppliers(String nccID, String supplierName, String supplierAddress, String supplierEmail, String sdtNCC) {
        this.SupplierID = nccID;
        this.SupplierName = supplierName;
        this.SupplierAddress = supplierAddress;
        this.SupplierEmail = supplierEmail;
        this.SupplierPhoneNo = sdtNCC;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String nccID) {
        this.SupplierID = nccID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        this.SupplierName = supplierName;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.SupplierAddress = supplierAddress;
    }

    public String getSupplierEmail() {
        return SupplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.SupplierEmail = supplierEmail;
    }

    public String getSupplierPhoneNo() {
        return SupplierPhoneNo;
    }

    public void setSupplierPhoneNo(String supplierPhoneNo) {
        this.SupplierPhoneNo = supplierPhoneNo;
    }
}
