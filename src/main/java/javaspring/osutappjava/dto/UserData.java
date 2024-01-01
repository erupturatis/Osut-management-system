package javaspring.osutappjava.dto;

public class UserData {
    private String username;
    private String userType;

    public UserData(String username, String userType) {
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }
}
