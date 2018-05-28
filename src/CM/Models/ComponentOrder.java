package CM.Models;

import java.util.Date;

public class ComponentOrder {
    private String CompOrderID;
    private String SupplierID;
    private String EmployeeID;
    private Date PublishDate;

    public ComponentOrder() {}

    public ComponentOrder(String compOrderID, Date publishDate, String employeeID, String supplierID) {
        CompOrderID = compOrderID;
        SupplierID = supplierID;
        EmployeeID = employeeID;
        PublishDate = publishDate;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getSupplierID() { return SupplierID; }

    public void setSupplierID(String supplierID) { SupplierID = supplierID; }

    public String getEmployeeID() { return EmployeeID; }

    public void setEmployeeID(String employeeID) {  EmployeeID = employeeID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }
}
