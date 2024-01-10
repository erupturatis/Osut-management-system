package javaspring.osutappjava.dto.user;

public class UserAuthCredentials {
    // username, password

    private String username;
    private String password;

    public UserAuthCredentials() {
    }

    public UserAuthCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
