package CM.Models;

public class Employees {
    private String MaNV;
    private String TenNV;
    private String Phai;
    private String MaPhong;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String NVID) {
        this.MaNV = NVID;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        this.TenNV = tenNV;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String PBID) {
        this.MaPhong = PBID;
    }

    public String getPhai() {
        return Phai;
    }

    public void setPhai(String phai) {
        this.Phai = phai;
    }

    public Employees() {}

    public Employees(String maNV, String tenNV, String phai, String maPhong) {
        MaNV = maNV;
        TenNV = tenNV;
        Phai = phai;
        MaPhong = maPhong;
    }
}
