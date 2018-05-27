package CM.Models;

public class LogIn {
    private String Username;
    private String Password;
    private String SignInID;
    private String DisplayName;
    private String AuthID;

    public LogIn() {}

    public LogIn(String signInID, String displayName, String username, String password, String authID) {
        Username = username;
        Password = password;
        SignInID = signInID;
        DisplayName = displayName;
        AuthID = authID;
    }

    public String getUsername() { return Username; }

    public void setUsername(String username) { Username = username; }

    public String getPassword() { return Password; }

    public void setPassword(String password) { Password = password; }

    public String getSignInID() { return SignInID; }

    public void setSignInID(String signInID) { SignInID = signInID; }

    public String getDisplayName() { return DisplayName; }

    public void setDisplayName(String displayName) { DisplayName = displayName; }

    public String getAuthID() { return AuthID; }

    public void setAuthID(String authID) { AuthID = authID; }
}
