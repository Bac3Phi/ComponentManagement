package CM.Models;

public class Department {
    private String PBID;
    private String TenPB;

    public String getPBID() {
        return PBID;
    }

    public void setPBID(String PBID) {
        this.PBID = PBID;
    }

    public String getTenPB() {
        return TenPB;
    }

    public void setTenPB(String tenPB) {
        this.TenPB = tenPB;
    }

    public Department() {}

    public Department(String PBID, String tenPB) {
        this.PBID = PBID;
        this.TenPB = tenPB;
    }
}
