package CM.Models;

import java.util.Date;

public class ComponentOrder {
    private String CompOrderID;
    private String ProviderName;
    private String EmployeeName;
    private Date PublishDate;

    public ComponentOrder() {}

    public ComponentOrder(String compOrderID, Date publishDate, String employeeName, String providerName) {
        CompOrderID = compOrderID;
        ProviderName = providerName;
        EmployeeName = employeeName;
        PublishDate = publishDate;
    }

    public String getCompOrderID() { return CompOrderID; }

    public void setCompOrderID(String compOrderID) { CompOrderID = compOrderID; }

    public String getProviderName() { return ProviderName; }

    public void setProviderName(String providerName) { ProviderName = providerName; }

    public String getEmployeeName() { return EmployeeName; }

    public void setEmployeeName(String employeeName) {  EmployeeName = employeeName; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }
}
