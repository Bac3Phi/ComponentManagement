package CM.Models;

import java.util.Date;

public class OrderInFo {
    private String MaDDH;
    private String MaCTDDH;
    private String MaMH;
    private int SoLuong;
    private String DonGia;


    public OrderInFo() {}

    public OrderInFo(String maCTDDH, String donGia, int soLuong, String maDDH, String maMH) {
        MaDDH = maDDH;
        MaCTDDH = maCTDDH;
        MaMH = maMH;
        SoLuong = soLuong;
        DonGia = donGia;
    }

    public String getMaDDH() { return MaDDH; }

    public void setMaDDH(String maDDH) { MaDDH = maDDH; }

    public String getMaCTDDH() { return MaCTDDH; }

    public void setMaCTDDH(String maCTDDH) { MaCTDDH = maCTDDH; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }

    public int getSoLuong() { return SoLuong;  }

    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public String getDonGia() { return DonGia; }

    public void setDonGia(String donGia) { DonGia = donGia; }
}
