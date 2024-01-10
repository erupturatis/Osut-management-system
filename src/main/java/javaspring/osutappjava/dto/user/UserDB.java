package javaspring.osutappjava.dto.user;


public class UserDB {
    // user_id, user_password, age, is_admin, able_to_work
    private String user_id;
    private String user_password;
    private int age;
    private boolean is_admin;
    private boolean able_to_work;

    public UserDB() {
    }

    public UserDB(String user_id, String user_password, int age, boolean is_admin, boolean able_to_work) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.age = age;
        this.is_admin = is_admin;
        this.able_to_work = able_to_work;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public int getAge() {
        return age;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public boolean isAble_to_work() {
        return able_to_work;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public void setAble_to_work(boolean able_to_work) {
        this.able_to_work = able_to_work;
    }

}
