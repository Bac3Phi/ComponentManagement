package CM.Models;

public class Provider {
    private String NCCID;
    private String TenNCC;
    private String DiaChiNCC;
    private String EmailNCC;
    private String sdtNCC;

    public Provider() {}

    public Provider(String nccID, String tenNCC, String diaChiNCC, String emailNCC, String sdtNCC) {
        this.NCCID = nccID;
        this.TenNCC = tenNCC;
        this.DiaChiNCC = diaChiNCC;
        this.EmailNCC = emailNCC;
        this.sdtNCC = sdtNCC;
    }

    public String getNCCID() {
        return NCCID;
    }

    public void setNCCID(String nccID) {
        this.NCCID = nccID;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.TenNCC = tenNCC;
    }

    public String getDiaChiNCC() {
        return DiaChiNCC;
    }

    public void setDiaChiNCC(String diaChiNCC) {
        this.DiaChiNCC = diaChiNCC;
    }

    public String getEmailNCC() {
        return EmailNCC;
    }

    public void setEmailNCC(String emailNCC) {
        this.EmailNCC = emailNCC;
    }

    public String getSdtNCC() {
        return sdtNCC;
    }

    public void setSdtNCC(String sdtNCC) {
        this.sdtNCC = sdtNCC;
    }
}
