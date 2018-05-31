package CM.Models;

import java.util.Date;

public class ComponentOrder {
    private String CompOrderID;
    private String ProviderID;
    private String EmployeeID;
    private Date PublishDate;

    public ComponentOrder() {}

    public ComponentOrder(String compOrderID, Date publishDate, String employeeID, String providerID) {
        CompOrderID = compOrderID;
        ProviderID = providerID;
        EmployeeID = employeeID;
        PublishDate = publishDate;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getProviderID() { return ProviderID; }

    public void setProviderID(String providerID) { ProviderID = providerID; }

    public String getEmployeeID() { return EmployeeID; }

    public void setEmployeeID(String employeeID) {  EmployeeID = employeeID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }
}
