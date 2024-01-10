package javaspring.osutappjava.variables;

import org.springframework.stereotype.Component;

@Component
public class PathsVariables {

    public static final String AUTH_PATH = "/auth-view";
    public static final String USER_ADMIN_PATH = "/admin";
    public static final String USER_MEMBER_PATH = "/user/{name}";
    public static final String USER_EDIT_PATH = "/user/{name}/edit";
    public static final String USER_CREATE_PATH = "/user/create";
    public static final String DEPARTMENT_PATH = "/department/{department_id}";
    public static final String LOGOUT_PATH = "/logout";
    public static final String AUTH_POST_PATH = "/auth";
    public static final String PROJECT_PATH = "/project/{project_id}";


    public String getUserCreatePath() {
        return USER_CREATE_PATH;
    }

    public String getAuthPath() {
        return AUTH_PATH;
    }

    public String getUserAdminPath() {
        return USER_ADMIN_PATH;
    }

    public String getUserMemberPath() {
        return USER_MEMBER_PATH;
    }


    public String buildUserMemberEditPath(String name) {
        String userMemberPath = USER_EDIT_PATH.replace("{name}", name);
        return userMemberPath;
    }

    public String buildUserMemberPath(String name) {
        String userMemberPath = USER_MEMBER_PATH.replace("{name}", name);
        return userMemberPath;
    }

    public String buildProjectPath(String project_id) {
        String projectPath = PROJECT_PATH.replace("{project_id}", project_id);
        return projectPath;
    }

    public String prefixRedirect() {
        return "redirect:";
    }
}
