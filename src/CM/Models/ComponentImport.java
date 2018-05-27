package CM.Models;

import java.util.Date;

public class ComponentImport {
    private String CompImportID;
    private Date PublishDate;
    private String EmployeeID;

    public ComponentImport(String compImportID, Date publishDate, String employeeID) {
        CompImportID = compImportID;
        PublishDate = publishDate;
        EmployeeID = employeeID;
    }

    public String getCompImportID() { return CompImportID; }

    public void setCompImportID(String compImportID) { CompImportID = compImportID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }

    public String getEmployeeID() { return EmployeeID; }

    public void setEmployeeID(String employeeID) { EmployeeID = employeeID; }
}
