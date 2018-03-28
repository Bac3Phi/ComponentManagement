package CM.Models;

public class Customer {


    String KHID;
    String TenKH;
    String DiaChi;
    String Email;
    String Sodienthoai;

    public String getKHID() {
        return KHID;
    }

    public void setKHID(String KHID) {
        this.KHID = KHID;
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

    public String getSodienthoai() {
        return Sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        Sodienthoai = sodienthoai;
    }


    public Customer(String KHID, String tenKH, String diaChi, String email, String sodienthoai) {
        this.KHID = KHID;
        TenKH = tenKH;
        DiaChi = diaChi;
        Email = email;
        Sodienthoai = sodienthoai;
    }
}
