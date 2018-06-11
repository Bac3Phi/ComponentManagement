package CM.Models;

public class Employees {
    private String EmployeeID;
    private String EmployeeName;
    private String EmployeeGender;
    private String DepartmentName;

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String NVID) {
        this.EmployeeID = NVID;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.EmployeeName = employeeName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String PBID) {
        this.DepartmentName = PBID;
    }

    public String getEmployeeGender() {
        return EmployeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.EmployeeGender = employeeGender;
    }

    public Employees() {}

    public Employees(String employeeID, String employeeName, String employeeGender, String departmentName) {
        EmployeeID = employeeID;
        EmployeeName = employeeName;
        EmployeeGender = employeeGender;
        DepartmentName = departmentName;
    }
}
