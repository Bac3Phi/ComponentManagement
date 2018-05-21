package CM.Models;

public class Supplier {
    private String MaNCC;
    private String TenNCC;
    private String DiaChiNCC;
    private String EmailNCC;
    private String SoDTNCC;

    public Supplier() {}

    public Supplier(String nccID, String tenNCC, String diaChiNCC, String emailNCC, String sdtNCC) {
        this.MaNCC = nccID;
        this.TenNCC = tenNCC;
        this.DiaChiNCC = diaChiNCC;
        this.EmailNCC = emailNCC;
        this.SoDTNCC = sdtNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String nccID) {
        this.MaNCC = nccID;
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

    public String getSoDTNCC() {
        return SoDTNCC;
    }

    public void setSoDTNCC(String soDTNCC) {
        this.SoDTNCC = soDTNCC;
    }
}
