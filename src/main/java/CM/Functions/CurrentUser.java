package CM.Functions;

public class CurrentUser {
    protected static String username,displayname,authname;
    public CurrentUser(String DisplayName, String UserName, String AuthName){
        this.username = UserName;
        this.displayname = DisplayName;
        this.authname = AuthName;
    }
    public  static String getID(){
        return username;
    }
    public static String getDisplayName(){
        return displayname;
    }
    public static String getAuthname(){
        return authname;
    }
}
