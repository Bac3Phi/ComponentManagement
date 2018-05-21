package CM.Models;

public class OutputInfo {
    private String MaCTPX;
    private int SoLuong;
    private String GhiChu;
    private String MaMH;
    private String MaKH;
    private String MaCTPN;
    private String MaPX;

    public OutputInfo(String maCTPX, int soLuong, String ghiChu, String maMH, String maKH, String maCTPN, String maPX) {
        MaCTPX = maCTPX;
        SoLuong = soLuong;
        GhiChu = ghiChu;
        MaMH = maMH;
        MaKH = maKH;
        MaCTPN = maCTPN;
        MaPX = maPX;
    }

    public String getMaCTPX() { return MaCTPX; }

    public void setMaCTPX(String maCTPX) { MaCTPX = maCTPX; }

    public int getSoLuong() { return SoLuong; }

    public void setSoLuong(int soLuong) { SoLuong = soLuong; }

    public String getGhiChu() { return GhiChu; }

    public void setGhiChu(String ghiChu) { GhiChu = ghiChu; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }

    public String getMaKH() { return MaKH; }

    public void setMaKH(String maKH) { MaKH = maKH; }

    public String getMaCTPN() { return MaCTPN; }

    public void setMaCTPN(String maCTPN) { MaCTPN = maCTPN; }

    public String getMaPX() { return MaPX; }

    public void setMaPX(String maPX) { MaPX = maPX; }
}
