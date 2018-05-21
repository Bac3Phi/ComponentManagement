package CM.Models;

import java.util.Date;

public class Output {
    private String MaPX;
    private Date NgayLapPhieu;

    public Output(String maPX, Date ngayLapPhieu) {
        MaPX = maPX;
        NgayLapPhieu = ngayLapPhieu;
    }

    public String getMaPX() { return MaPX; }

    public void setMaPX(String maPX) { MaPX = maPX; }

    public Date getNgayLapPhieu() { return NgayLapPhieu; }

    public void setNgayLapPhieu(Date ngayLapPhieu) { NgayLapPhieu = ngayLapPhieu; }
}
