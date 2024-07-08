package model;

public class Admin {
    private Admin() {};
    private static Admin admin = new Admin();
    private Password password = new Password("SUT@EEoop");

    public Password getPassword() {
        return password;
    }

    public static Admin getInstance() {
        return  admin;
    }

}
