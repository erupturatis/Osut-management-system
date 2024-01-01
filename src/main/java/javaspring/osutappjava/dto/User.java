package javaspring.osutappjava.dto;


public class User {
    // user_id

    private String user_id;


    // the other methods

    public User() {
    }

    public User(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
