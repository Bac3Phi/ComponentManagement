package CM.Models;

import java.util.Date;

public class ReceiptsAndPaymentsReport {
    private String ReportID;
    private Date PublishDate;
    private int SumReceipts;
    private int SumPayments;
    private String EmployeeID;
    private String BillID;
    private String CompImportID;
    private int ReportMonth;

    public ReceiptsAndPaymentsReport(String reportID, Date publishDate, int sumReceipts, int sumPayments,
                                     String employeeID, String billID, String compImportID, int reportMonth) {
        ReportID = reportID;
        PublishDate = publishDate;
        SumReceipts = sumReceipts;
        SumPayments = sumPayments;
        EmployeeID = employeeID;
        BillID = billID;
        CompImportID = compImportID;
        ReportMonth = reportMonth;
    }

    public String getReportID() { return ReportID; }

    public void setReportID(String reportID) { ReportID = reportID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }

    public int getSumReceipts() { return SumReceipts; }

    public void setSumReceipts(int sumReceipts) { SumReceipts = sumReceipts; }

    public int getSumPayments() { return SumPayments; }

    public void setSumPayments(int sumPayments) { SumPayments = sumPayments; }

    public String getEmployeeID() { return EmployeeID; }

    public void setEmployeeID(String employeeID) { EmployeeID = employeeID; }

    public String getBillID() { return BillID; }

    public void setBillID(String billID) { BillID = billID; }

    public String getCompImportID() { return CompImportID; }

    public void setCompImportID(String compImportID) { CompImportID = compImportID; }

    public int getReportMonth() { return ReportMonth; }

    public void setReportMonth(int reportMonth) { ReportMonth = reportMonth; }
}
