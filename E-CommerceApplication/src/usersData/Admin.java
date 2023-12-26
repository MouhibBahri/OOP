package usersData;

public class Admin extends UserModel {
    public Admin(String fullname, String email, String username, String password) {
        super(fullname, email, username, password);
    }

    public String toString() {
        return "Type: Admin\n" + super.toString();
    }

}
