package CM.Models;

import java.util.Date;

public class Receipt {
    private String MaHD;
    private String MaKH;
    private Date NgayLap;
    private String MaNV;
    private String MaSoThue;
    private String TienThanhToan;
    private String LoaiHD;

    public Receipt() {}

    public Receipt(String hdid, Date ngl, String mst, String tientt, String loaihd, String nvid, String khid) {
        this.MaHD = hdid;
        this.MaKH = khid;
        this.NgayLap = ngl;
        this.MaNV = nvid;
        this.MaSoThue = mst;
        this.TienThanhToan = tientt;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        this.MaHD = maHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        this.MaKH = maKH;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngl) {
        this.NgayLap = ngl;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String nvid) {
        this.MaNV = nvid;
    }

    public String getMaSoThue() {
        return MaSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.MaSoThue = maSoThue;
    }

    public String getTienThanhToan() {
        return TienThanhToan;
    }

    public void setTienThanhToan(String tienThanhToan) {
        this.TienThanhToan = tienThanhToan;
    }

    public String getLoaiHD() { return LoaiHD; }

    public void setLoaiHD(String loaiHD) { LoaiHD = loaiHD; }
}
