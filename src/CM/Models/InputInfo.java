package CM.Models;

public class InputInfo {
    private String MaCTPN;
    private int SoLuong;
    private String DonGia;
    private String DonGiaBan;
    private String GhiChu;
    private String MaMH;
    private String MaPN;

    public InputInfo(String maCTPN, int soLuong, String donGia, String donGiaBan, String ghiChu, String maMH, String maPN) {
        MaCTPN = maCTPN;
        SoLuong = soLuong;
        DonGia = donGia;
        DonGiaBan = donGiaBan;
        GhiChu = ghiChu;
        MaMH = maMH;
        MaPN = maPN;
    }

    public String getMaCTPN() { return MaCTPN; }

    public void setMaCTPN(String maCTPN) { MaCTPN = maCTPN; }

    public int getSoLuong() { return SoLuong; }

    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public String getDonGia() { return DonGia; }

    public void setDonGia(String donGia) { DonGia = donGia; }

    public String getDonGiaBan() { return DonGiaBan; }

    public void setDonGiaBan(String donGiaBan) { DonGiaBan = donGiaBan; }

    public String getGhiChu() { return GhiChu; }

    public void setGhiChu(String ghiChu) { GhiChu = ghiChu; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }

    public String getMaPN() { return MaPN; }

    public void setMaPN(String maPN) { MaPN = maPN; }
}
