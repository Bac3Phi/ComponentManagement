package CM.Models;

public class LogIn {
    private String Username;
    private String Password;
    private String MaDN;
    private String TenHienThi;
    private String MaPQ;

    public LogIn() {}

    public LogIn(String maDN, String tenHienThi, String username, String password, String maPQ) {
        Username = username;
        Password = password;
        MaDN = maDN;
        TenHienThi = tenHienThi;
        MaPQ = maPQ;
    }

    public String getUsername() { return Username; }

    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }

    public void setPassword(String password) { Password = password; }

    public String getMaDN() { return MaDN; }

    public void setMaDN(String maDN) { MaDN = maDN; }

    public String getTenHienThi() { return TenHienThi; }

    public void setTenHienThi(String tenHienThi) { TenHienThi = tenHienThi; }

    public String getMaPQ() { return MaPQ; }

    public void setMaPQ(String maPQ) { MaPQ = maPQ; }
}
