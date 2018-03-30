package CM.Models;

import java.util.Date;

public class OrderForm {
    private String DHID;
    private Date ngaylap;
    private String NCCID;
    private String NVID;

    public OrderForm() {}

    public OrderForm(String dhid, Date ngl, String nccid, String nvid){
        this.DHID = dhid;
        this.ngaylap = ngl;
        this.NCCID = nccid;
        this.NVID = nvid;
    }

    public String getDHID() {
        return DHID;
    }

    public void setDHID(String DHID) {
        this.DHID = DHID;
    }

    public String getNVID() {
        return NVID;
    }

    public void setNVID(String nvid) {
        this.NVID = nvid;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date nl) {
        this.ngaylap = nl;
    }

    public String getNCCID() {
        return NCCID;
    }

    public void setNCCID(String nccid) {
        this.NCCID = nccid;
    }
}
