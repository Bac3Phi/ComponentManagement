package CM.Models;

import java.util.Date;

public class Order {
    private String MaDDH;
    private String MaNCC;
    private String MaNV;
    private Date NgayLap;

    public Order() {}

    public Order(String maDDH, Date ngayLap, String maNV, String maNCC) {
        MaDDH = maDDH;
        MaNCC = maNCC;
        MaNV = maNV;
        NgayLap = ngayLap;
    }

    public String getMaDDH() { return MaDDH; }

    public void setMaDDH(String maDDH) { MaDDH = maDDH; }

    public String getMaNCC() { return MaNCC; }

    public void setMaNCC(String maNCC) { MaNCC = maNCC; }

    public String getMaNV() { return MaNV; }

    public void setMaNV(String maNV) {  MaNV = maNV; }

    public Date getNgayLap() { return NgayLap; }

    public void setNgayLap(Date ngayLap) { NgayLap = ngayLap; }
}
