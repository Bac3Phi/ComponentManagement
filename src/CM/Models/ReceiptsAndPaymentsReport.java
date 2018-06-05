package CM.Models;

import java.util.Date;

public class ReceiptsAndPaymentsReport {
    private String ReportID;
    private Date PublishDate;
    private int SumReceipts;
    private int SumPayments;
    private String EmployeeName;
    private String Type;
    private String Date;

    public ReceiptsAndPaymentsReport(String reportID, java.util.Date publishDate, int sumReceipts, int sumPayments, String employeeName, String type, String date) {
        ReportID = reportID;
        PublishDate = publishDate;
        SumReceipts = sumReceipts;
        SumPayments = sumPayments;
        EmployeeName = employeeName;
        Type = type;
        Date = date;
    }

    public String getReportID() {
        return ReportID;
    }

    public void setReportID(String reportID) {
        ReportID = reportID;
    }

    public java.util.Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(java.util.Date publishDate) {
        PublishDate = publishDate;
    }

    public int getSumReceipts() {
        return SumReceipts;
    }

    public void setSumReceipts(int sumReceipts) {
        SumReceipts = sumReceipts;
    }

    public int getSumPayments() {
        return SumPayments;
    }

    public void setSumPayments(int sumPayments) {
        SumPayments = sumPayments;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
