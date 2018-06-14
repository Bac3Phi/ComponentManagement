package CM.Models;

import java.util.Date;

public class ComponentImport {
    private String CompImportId;
    private String OrderId;
    private Date PublishDate;
    private String EmployeeName;
    private String Amount;

    public ComponentImport(String compImportId, String orderId, Date publishDate, String employeeName, String amount) {
        CompImportId = compImportId;
        OrderId = orderId;
        PublishDate = publishDate;
        EmployeeName = employeeName;
        Amount = amount;
    }

    public String getCompImportId() {
        return CompImportId;
    }

    public void setCompImportId(String compImportId) {
        CompImportId = compImportId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
