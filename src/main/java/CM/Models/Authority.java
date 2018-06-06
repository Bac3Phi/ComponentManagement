package CM.Models;

public class Authority {
    private String AuthID;
    private String AuthName;

    public Authority(String authID, String authName) {
        AuthID = authID;
        AuthName = authName;
    }

    public String getAuthID() { return AuthID; }

    public void setAuthID(String authID) { AuthID = authID; }

    public String getAuthName() { return AuthName; }

    public void setAuthName(String authName) { AuthName = authName; }
}
