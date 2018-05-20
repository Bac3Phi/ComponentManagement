package CM.Models;

import java.util.Date;

public class Warranty {
    private String MaPBH;
    private Date NgayLap;
    private String GhiChu;
    private String MaMH;
    private String MaHD;

    public Warranty(){}

    public Warranty(String maPBH, Date ngayLap, String ghiChu, String maMH, String maHD) {
        MaPBH = maPBH;
        NgayLap = ngayLap;
        GhiChu = ghiChu;
        MaMH = maMH;
        MaHD = maHD;
    }

    public String getMaPBH() { return MaPBH; }

    public void setMaPBH(String maPBH) { MaPBH = maPBH; }

    public Date getNgayLap() { return NgayLap; }

    public void setNgayLap(Date ngayLap) { NgayLap = ngayLap; }

    public String getGhiChu() { return GhiChu; }

    public void setGhiChu(String ghiChu) { GhiChu = ghiChu; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }

    public String getMaHD() { return MaHD; }

    public void setMaHD(String maHD) { MaHD = maHD; }
}
