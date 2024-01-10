package javaspring.osutappjava.dto.user;

public class UserCookieData {
    private String username;
    private boolean isAdmin;

    public UserCookieData(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }


    public String getUsername() {
        return username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
