package CM.Models;

public class Authority {
    private String MaPQ;
    private String TenPQ;

    public Authority(String maPQ, String tenPQ) {
        MaPQ = maPQ;
        TenPQ = tenPQ;
    }

    public String getMaPQ() { return MaPQ; }

    public void setMaPQ(String maPQ) { MaPQ = maPQ; }

    public String getTenPQ() { return TenPQ; }

    public void setTenPQ(String tenPQ) { TenPQ = tenPQ; }
}
