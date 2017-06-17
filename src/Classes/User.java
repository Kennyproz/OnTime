package Classes;

/**
 * Created by Ken on 9-6-2017.
 */
public class User {

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
