package CM.Models;

public class ReceiptInfo {
    private String MaCTHD;
    private String DonGiaBan;
    private int SoLuong;
    private String MaHD;
    private String MaMH;

    public ReceiptInfo(String maCTHD, String donGiaBan, int soLuong, String maHD, String maMH) {
        MaCTHD = maCTHD;
        DonGiaBan = donGiaBan;
        SoLuong = soLuong;
        MaHD = maHD;
        MaMH = maMH;
    }

    public String getMaCTHD() { return MaCTHD; }

    public void setMaCTHD(String maCTHD) { MaCTHD = maCTHD; }

    public String getDonGiaBan() { return DonGiaBan; }

    public void setDonGiaBan(String donGiaBan) { DonGiaBan = donGiaBan; }

    public int getSoLuong() { return SoLuong; }

    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public String getMaHD() { return MaHD; }

    public void setMaHD(String maHD) { MaHD = maHD; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }
}
