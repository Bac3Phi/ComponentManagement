package CM.Models;

public class Inventories {
    private String MaKhu;
    private int SoLuongTon;
    private String MaMH;

    public Inventories(String maKhu, int soLuongTon, String maMH) {
        MaKhu = maKhu;
        SoLuongTon = soLuongTon;
        MaMH = maMH;
    }

    public String getMaKhu() { return MaKhu; }

    public void setMaKhu(String maKhu) { MaKhu = maKhu; }

    public int getSoLuongTon() { return SoLuongTon; }

    public void setSoLuongTon(int soLuongTon) { SoLuongTon = soLuongTon; }

    public String getMaMH() { return MaMH; }

    public void setMaMH(String maMH) { MaMH = maMH; }
}
