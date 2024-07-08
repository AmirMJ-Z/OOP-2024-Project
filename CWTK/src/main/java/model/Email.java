package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;

public class Email implements Serializable {
    @Serial
    private static final long serialVersionUID = 2904748097200017082L;
    private String email;
    private String name;
    private String domain;
    private static Matcher matcher;
    private static String regex = "^(?<name>\\S+)@(?<domain>\\S+).com$";

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public Email(String email) {
        this.email = email.trim();
        matcher = App.getCommandMatcher(email, regex);
        matcher.find();
        name = matcher.group("name");
        domain = matcher.group("domain");
    }

    public static boolean isEmail(String email) {
        Matcher matcher1 = App.getCommandMatcher(email, regex);
        return matcher1.find();
    }

    public String toString() {
        return email;
    }
}
