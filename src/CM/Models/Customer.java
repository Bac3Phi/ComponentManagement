package CM.Models;

public class Customer {
    String MaKH;
    String TenKH;
    String DiaChi;
    String Email;
    String SoDT;

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String sodienthoai) {
        SoDT = sodienthoai;
    }


    public Customer(String khID, String tenKH, String diaChi, String email, String sodienthoai) {
        MaKH = khID;
        TenKH = tenKH;
        DiaChi = diaChi;
        Email = email;
        SoDT = sodienthoai;
    }
}
