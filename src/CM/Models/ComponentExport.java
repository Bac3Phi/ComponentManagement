package CM.Models;

import java.util.Date;

public class ComponentExport {
    private String CompExportID;
    private Date PublishDate;
    private String BillID;
    private String CompImportInfoID;

    public ComponentExport(String compExportID, Date publishDate, String billID, String compImportInfoID) {
        CompExportID = compExportID;
        PublishDate = publishDate;
        BillID = billID;
        CompImportInfoID = compImportInfoID;
    }

    public String getCompExportID() { return CompExportID; }

    public void setCompExportID(String compExportID) { CompExportID = compExportID; }

    public Date getPublishDate() { return PublishDate; }

    public void setPublishDate(Date publishDate) { PublishDate = publishDate; }

    public String getBillID() { return BillID; }

    public void setBillID(String billID) { BillID = billID; }

    public String getCompImportInfoID() { return CompImportInfoID; }

    public void setCompImportInfoID(String compImportInfoID) { CompImportInfoID = compImportInfoID; }
}
