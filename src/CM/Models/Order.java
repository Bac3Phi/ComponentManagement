package CM.Models;

public class Order {
    private String DHID;
    private String MHID;
    private int soluong;

    public Order() {}

    public Order(String dhid, String mhid, int sl) {
        this.DHID = dhid;
        this.MHID = mhid;
        this.soluong = sl;
    }

    public String getDHID() {
        return DHID;
    }

    public void setDHID(String DHID) {
        this.DHID = DHID;
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
