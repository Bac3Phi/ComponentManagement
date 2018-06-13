package CM.Models;

import java.util.Date;

public class Bills {
    private String BillID;
    private String CustomerName;
    private Date PublishDate;
    private long SumMoney;
    private String EmployeeName;
    private String TaxCode;

    public Bills() {}

    public Bills(String hdid, Date ngl, String mst, long sumMoney, String nvname, String khid) {
        this.BillID = hdid;
        this.CustomerName = khid;
        this.PublishDate = ngl;
        this.EmployeeName = nvname;
        this.TaxCode = mst;
        this.SumMoney = sumMoney;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        this.BillID = billID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date ngl) {
        this.PublishDate = ngl;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String nvid) {
        this.EmployeeName = nvid;
    }

    public String getTaxCode() {
        return TaxCode;
    }

    public void setTaxCode(String taxCode) {
        this.TaxCode = taxCode;
    }

    public long getSumMoney() {
        return SumMoney;
    }

    public void setSumMoney(long sumMoney) {
        SumMoney = sumMoney;
    }
}
