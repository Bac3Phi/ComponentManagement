package CM.Models;

public class Goods {
    private String MHID;
    private String TenMH;
    private String donvitinh;
    private String dongia;

    public Goods(){}

    public Goods(String MHID, String tenMH, String donvitinh, String dongia){
        this.MHID = MHID;
        this.TenMH = tenMH;
        this.donvitinh = donvitinh;
        this.dongia = dongia;
    }

    public String getMHID() {
        return MHID;
    }

    public void setMHID(String MHID) {
        this.MHID = MHID;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        this.TenMH = tenMH;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }
}
