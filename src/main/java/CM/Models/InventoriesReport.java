package CM.Models;

import java.util.Date;

public class InventoriesReport {
    private String ReportID;
    private Date PublishDate;
    private int ReportMonth;
    private String EmployeeName;
    private int SumStock;
    private int SumImport;
    private int SumSell;

    public InventoriesReport(String reportID, Date publishDate, int reportMonth, String employeeName,
                             int sumImport, int sumSell, int sumStock) {
        ReportID = reportID;
        PublishDate = publishDate;
        ReportMonth = reportMonth;
        SumStock = sumStock;
        SumImport = sumImport;
        SumSell = sumSell;
        EmployeeName = employeeName;
    }

    public String getReportID() { return ReportID; }

    public void setReportID(String reportID) { ReportID = reportID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }

    public int getReportMonth() { return ReportMonth; }

    public void setReportMonth(int reportMonth) { ReportMonth = reportMonth; }

    public int getSumStock() { return SumStock; }

    public void setSumStock(int sumStock) { SumStock = sumStock; }

    public int getSumImport() { return SumImport; }

    public void setSumImport(int sumImport) { SumImport = sumImport; }

    public int getSumSell() { return SumSell; }

    public void setSumSell(int sumSell) { SumSell = sumSell; }

    public String getEmployeeName() { return EmployeeName; }

    public void setEmployeeName(String employeeName) { EmployeeName = employeeName; }
}
