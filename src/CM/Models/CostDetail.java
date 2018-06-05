package CM.Models;

public class CostDetail {
    private String CostDetailId;
    private String ReportId;
    private String CompName;
    private String TotalCostDetail;

    public CostDetail(String costDetailId, String reportId, String compName, String totalCostDetail) {
        CostDetailId = costDetailId;
        ReportId = reportId;
        CompName = compName;
        TotalCostDetail = totalCostDetail;
    }

    public String getCostDetailId() {
        return CostDetailId;
    }

    public void setCostDetailId(String costDetailId) {
        CostDetailId = costDetailId;
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

    public String getTotalCostDetail() {
        return TotalCostDetail;
    }

    public void setTotalCostDetail(String totalCostDetail) {
        TotalCostDetail = totalCostDetail;
    }
}
