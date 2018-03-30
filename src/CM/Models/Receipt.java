package CM.Models;

import java.util.Date;

public class Receipt {
    private String HDID;
    private String KHID;
    private Date ngaylap;
    private String NVID;
    private String masothue;
    private String sotientt;

    public Receipt() {}

    public Receipt(String hdid, String khid, Date ngl, String nvid, String mst, String tientt) {
        this.HDID = hdid;
        this.KHID = khid;
        this.ngaylap = ngl;
        this.NVID = nvid;
        this.masothue = mst;
        this.sotientt = tientt;
    }

    public String getHDID() {
        return HDID;
    }

    public void setHDID(String HDID) {
        this.HDID = HDID;
    }

    public String getKHID() {
        return KHID;
    }

    public void setKHID(String KHID) {
        this.KHID = KHID;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngl) {
        this.ngaylap = ngl;
    }

    public String getNVID() {
        return NVID;
    }

    public void setNVID(String nvid) {
        this.NVID = nvid;
    }

    public String getMasothue() {
        return masothue;
    }

    public void setMasothue(String masothue) {
        this.masothue = masothue;
    }

    public String getSotientt() {
        return sotientt;
    }

    public void setSotientt(String sotientt) {
        this.sotientt = sotientt;
    }
}
