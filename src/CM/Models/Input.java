package CM.Models;

import java.util.Date;

public class Input {
    private String MaPN;
    private Date NgayLapPhieu;

    public Input(String maPN, Date ngayLapPhieu) {
        MaPN = maPN;
        NgayLapPhieu = ngayLapPhieu;
    }

    public String getMaPN() { return MaPN; }

    public void setMaPN(String maPN) { MaPN = maPN; }

    public Date getNgayLapPhieu() { return NgayLapPhieu; }

    public void setNgayLapPhieu(Date ngayLapPhieu) { NgayLapPhieu = ngayLapPhieu; }
}
