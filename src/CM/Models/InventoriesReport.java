package CM.Models;

import java.util.Date;

public class InventoriesReport {
    private String ReportID;
    private String CompImportInfoID;
    private int InventoriesBefore;
    private int InventoriesAfter;
    private String StockID;
    private String BillID;
    private String EmployeeID;
    private int ReportMonth;
    private Date PublishDate;

    public InventoriesReport(String reportID, int inventoriesBefore, int inventoriesAfter, String compImportInfoID,
                             String stockID, String billID, String employeeID, int reportMonth, Date ngl) {
        ReportID = reportID;
        CompImportInfoID = compImportInfoID;
        InventoriesBefore = inventoriesBefore;
        InventoriesAfter = inventoriesAfter;
        StockID = stockID;
        BillID = billID;
        EmployeeID = employeeID;
        ReportMonth = reportMonth;
        PublishDate = ngl;
    }

    public String getReportID() { return ReportID; }

    public void setReportID(String reportID) { ReportID = reportID; }

    public String getCompImportInfoID() { return CompImportInfoID; }

    public void setCompImportInfoID(String compImportInfoID) { CompImportInfoID = compImportInfoID; }

    public int getInventoriesBefore() { return InventoriesBefore; }

    public void setInventoriesBefore(int inventoriesBefore) { InventoriesBefore = inventoriesBefore; }

    public int getInventoriesAfter() { return InventoriesAfter; }

    public void setInventoriesAfter(int inventoriesAfter) { InventoriesAfter = inventoriesAfter; }

    public String getStockID() { return StockID; }

    public void setStockID(String stockID) { StockID = stockID; }

    public String getBillID() { return BillID; }

    public void setBillID(String billID) { BillID = billID; }

    public String getEmployeeID() { return EmployeeID; }

    public void setEmployeeID(String employeeID) { EmployeeID = employeeID; }

    public int getReportMonth() { return ReportMonth; }

    public void setReportMonth(int reportMonth) { ReportMonth = reportMonth; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }
}
