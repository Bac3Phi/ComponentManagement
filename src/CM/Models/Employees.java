package CM.Models;

public class Employees {
    private String NVID;
    private String PBID;
    private String TenNV;

    public String getNVID() {
        return NVID;
    }

    public void setNVID(String NVID) {
        this.NVID = NVID;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        this.TenNV = tenNV;
    }

    public String getPBID() {
        return PBID;
    }

    public void setPBID(String PBID) {
        this.PBID = PBID;
    }

    public Employees() {}

    public Employees(String NVID, String PBID, String TenNV) {
        this.TenNV = TenNV;
        this.NVID = NVID;
        this.PBID = PBID;
    }
}
