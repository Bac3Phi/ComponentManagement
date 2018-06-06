package CM.Models;

public class RevenueDetail {
    private String RevenueDetailId;
    private String ReportId;
    private String CompName;
    private String TotalRevenueDetail;

    private RevenueDetail() {
    }

    public RevenueDetail(String revenueDetailId, String reportId, String compName, String totalRevenueDetail) {
        RevenueDetailId = revenueDetailId;
        ReportId = reportId;
        CompName = compName;
        TotalRevenueDetail = totalRevenueDetail;
    }

    public String getRevenueDetailId() {
        return RevenueDetailId;
    }

    public void setRevenueDetailId(String revenueDetailId) {
        RevenueDetailId = revenueDetailId;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public String getCompName() {
        return CompName;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public String getTotalRevenueDetail() {
        return TotalRevenueDetail;
    }

    public void setTotalRevenueDetail(String totalRevenueDetail) {
        TotalRevenueDetail = totalRevenueDetail;
    }
}
