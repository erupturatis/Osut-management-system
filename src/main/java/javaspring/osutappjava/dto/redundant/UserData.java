package javaspring.osutappjava.dto.redundant;

public class UserData {
    private String username;
    private String userType;

    public UserData() {
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
