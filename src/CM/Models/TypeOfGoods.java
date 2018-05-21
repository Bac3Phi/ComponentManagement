package CM.Models;

public class TypeOfGoods {
    private String MaLoai;
    private String TenLoai;

    public String getMaLoai() { return MaLoai; }

    public void setMaLoai(String maLoai) { MaLoai = maLoai; }

    public String getTenLoai() { return TenLoai; }

    public void setTenLoai(String tenLoai) { TenLoai = tenLoai; }

    public TypeOfGoods(String maLoai, String tenLoai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
    }
}
