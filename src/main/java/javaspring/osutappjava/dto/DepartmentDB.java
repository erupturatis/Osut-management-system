package javaspring.osutappjava.dto;

public class DepartmentDB {
    private String department_id;
    private String department_name;
    private String department_description;

    public DepartmentDB() {
    }

    public DepartmentDB(String departamentId, String departamentName, String departamentDescription) {
        this.department_id = departamentId;
        this.department_name = departamentName;
        this.department_description = departamentDescription;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_description() {
        return department_description;
    }

    public void setDepartment_description(String department_description) {
        this.department_description = department_description;
    }
}
