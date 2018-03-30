package CM.Models;

public class SellingGoods {
    private String HDID;
    private String MHID;
    private int soluong;

    public SellingGoods(){}

    public SellingGoods(String hdid, String mhid, int sl){
        this.HDID = hdid;
        this.MHID = mhid;
        this.soluong = sl;
    }

    public String getHDID() {
        return HDID;
    }

    public void setHDID(String HDID) {
        this.HDID = HDID;
    }

    public String getMHID() {
        return MHID;
    }

    public void setMHID(String MHID) {
        this.MHID = MHID;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int sl) {
        this.soluong = sl;
    }
}
