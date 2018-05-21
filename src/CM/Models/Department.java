package CM.Models;

public class Department {
    private String MaPhong;
    private String TenPhong;

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String PBID) {
        this.MaPhong = PBID;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPB) {
        this.TenPhong = tenPB;
    }

    public Department() {}

    public Department(String PBID, String tenPB) {
        this.MaPhong = PBID;
        this.TenPhong = tenPB;
    }
}
