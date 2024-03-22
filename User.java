package MiniProject;

public class User{
    private static int userID = 0;
    private String userName;
    private final String userEmail;
    private String userPwd;

    public User(String userName, String userEmail, String userPwd){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        userID++;
    }

    public String getUserName(){
        return userName;
    }

    public static int getUserID(){
        return userID;
    }

    public String getUserEmail(){
        return userEmail;
    }
    
    public String getUserPwd(){
        return userPwd;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }
}
