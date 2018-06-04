package CM.Models;

import java.util.Date;

public class InventoriesReportInfo {
    private String ReportInfoID;
    private int Import;
    private int Selling;
    private int Stock;
    private String ComponentID;
    private String ComponentName;
    private String ReportID;

    public InventoriesReportInfo(String reportInfoID, int anImport, int selling, int stock, String componentID, String reportID) {
        ReportInfoID = reportInfoID;
        Import = anImport;
        Selling = selling;
        Stock = stock;
        ComponentID = componentID;
        ReportID = reportID;
    }

    public String getReportInfoID() { return ReportInfoID; }

    public void setReportInfoID(String reportInfoID) { ReportInfoID = reportInfoID; }

    public int getImport() { return Import; }

    public void setImport(int anImport) { Import = anImport; }

    public int getSelling() { return Selling; }

    public void setSelling(int selling) { Selling = selling; }

    public int getStock() { return Stock; }

    public void setStock(int stock) { Stock = stock; }

    public String getComponentID() { return ComponentID; }

    public void setComponentID(String componentID) { ComponentID = componentID; }

    public String getReportID() { return ReportID; }

    public void setReportID(String reportID) { ReportID = reportID; }
}
