package javaspring.osutappjava.dto;

public class Project {
    // project_id, project_name, project_description, project_funding, department_id

    private String project_id;
    private String project_name;
    private String project_description;
    private int project_funding;
    private String department_id;

    public Project() {
    }

    public Project(String project_id, String project_name, String project_description, int project_funding, String department_id) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.project_funding = project_funding;
        this.department_id = department_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public int getProject_funding() {
        return project_funding;
    }

    public void setProject_funding(int project_funding) {
        this.project_funding = project_funding;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id='" + project_id + '\'' +
                ", project_name='" + project_name + '\'' +
                ", project_description='" + project_description + '\'' +
                ", project_funding=" + project_funding +
                ", department_id='" + department_id + '\'' +
                '}';
    }
}
