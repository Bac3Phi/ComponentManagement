package CM.Models;

public class Component {
    private String MaMH;
    private String TenHang;
    private String DonViTinh;
    private String HangSX;
    private String ThoiGianBaoHanh;
    private String HinhMinhHoa;
    private String MaLoai;

    public Component(){}

    public Component(String MHID, String tenMH, String donvitinh, String hangsx, String tgbh, String hinh, String maloai){
        this.MaMH = MHID;
        this.TenHang = tenMH;
        this.DonViTinh = donvitinh;
        this.HangSX = hangsx;
        this.ThoiGianBaoHanh = tgbh;
        this.HinhMinhHoa = hinh;
        this.MaLoai = maloai;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MHID) {
        this.MaMH = MHID;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenMH) {
        this.TenHang = tenMH;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String donvitinh) {
        this.DonViTinh = donvitinh;
    }

    public String getHangSX() {
        return HangSX;
    }

    public void setHangSX(String hangsx) {
        this.HangSX = hangsx;
    }

    public String getThoiGianBaoHanh() { return ThoiGianBaoHanh; }

    public void setThoiGianBaoHanh(String thoiGianBaoHanh) {
        ThoiGianBaoHanh = thoiGianBaoHanh;
    }

    public String getHinhMinhHoa() { return HinhMinhHoa; }

    public void setHinhMinhHoa(String hinhMinhHoa) {
        HinhMinhHoa = hinhMinhHoa;
    }

    public String getMaLoai() { return MaLoai; }

    public void setMaLoai(String maLoai) { MaLoai = maLoai; }
}
