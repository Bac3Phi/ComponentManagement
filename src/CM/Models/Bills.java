package CM.Models;

import java.util.Date;

public class Bills {
    private String BillID;
    private String CustomerID;
    private Date PublishDate;
    private String EmployeeID;
    private String TaxCode;
    private String PurchaseMoney;
    private String TypeOfBill;

    public Bills() {}

    public Bills(String hdid, Date ngl, String mst, String tientt, String loaihd, String nvid, String khid) {
        this.BillID = hdid;
        this.CustomerID = khid;
        this.PublishDate = ngl;
        this.EmployeeID = nvid;
        this.TaxCode = mst;
        this.PurchaseMoney = tientt;
        this.TypeOfBill = loaihd;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        this.BillID = billID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        this.CustomerID = customerID;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date ngl) {
        this.PublishDate = ngl;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String nvid) {
        this.EmployeeID = nvid;
    }

    public String getTaxCode() {
        return TaxCode;
    }

    public void setTaxCode(String taxCode) {
        this.TaxCode = taxCode;
    }

    public String getPurchaseMoney() {
        return PurchaseMoney;
    }

    public void setPurchaseMoney(String purchaseMoney) {
        this.PurchaseMoney = purchaseMoney;
    }

    public String getTypeOfBill() { return TypeOfBill; }

    public void setTypeOfBill(String typeOfBill) { TypeOfBill = typeOfBill; }
}
