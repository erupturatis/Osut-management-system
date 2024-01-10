package javaspring.osutappjava.dto;

public class RoleDB {
    // role_id, role_name, role_description

    private String role_id;
    private String role_name;
    private String role_description;

   // Default constructor
    public RoleDB() {
    }
    public RoleDB(String role_id, String role_name, String role_description) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_description = role_description;
    }

    public String getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

}
