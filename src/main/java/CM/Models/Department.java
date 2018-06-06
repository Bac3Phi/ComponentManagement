package CM.Models;

public class Department {
    private String DepartmentID;
    private String DepartmentName;

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String PBID) {
        this.DepartmentID = PBID;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String tenPB) {
        this.DepartmentName = tenPB;
    }

    public Department() {}

    public Department(String PBID, String tenPB) {
        this.DepartmentID = PBID;
        this.DepartmentName = tenPB;
    }
}
