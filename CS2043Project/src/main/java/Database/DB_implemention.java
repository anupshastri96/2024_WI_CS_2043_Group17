package Database;

public class DB_implemention implements DB_Methods{
    @Override
    public void addUser(String username, String password, String email) {
        DB_User.addUser(username, password, email);
    }
}
