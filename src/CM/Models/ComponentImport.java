package CM.Models;

import java.util.Date;

public class ComponentImport {
    private String CompImportId;
    private String OrderId;
    private String PublishDate;
    private String EmployeeName;
    private String Amount;

    public ComponentImport(String compImportId, String orderId, String publishDate, String employeeName, String amount) {
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

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
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
