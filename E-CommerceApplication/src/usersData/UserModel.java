package usersData;

public class UserModel {
    private final String fullname;
    private final String email;
    private final String username;
    private final String password;

    public UserModel(String fullname, String email, String username, String password) {
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    //getters
    public String getUsername() {
        return this.username;
    }

    public boolean login(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Full Name: " + fullname + "\n" +
                "Email: " + email + "\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n";
    }

}
