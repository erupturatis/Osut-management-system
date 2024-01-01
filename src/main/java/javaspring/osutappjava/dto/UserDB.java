package javaspring.osutappjava.dto;

public class UserDB {
    private String user_id;
    private String user_password;
    private boolean user_type;


    public UserDB() {
    }

    public UserDB(String user_id, String password, boolean user_type) {
        this.user_id = user_id;
        this.user_password = password;
        this.user_type = user_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public boolean getUser_type() {
        return user_type;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_type(boolean user_type) {
        this.user_type = user_type;
    }
}
