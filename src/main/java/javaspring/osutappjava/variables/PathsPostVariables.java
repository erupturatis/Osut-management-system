package javaspring.osutappjava.variables;

import org.springframework.stereotype.Component;

@Component
public class PathsPostVariables {

    public final static String REMOVE_DEPARTMENT_PATH = "/user/{name}/remove_department/{departmentId}";
    public final static String ADD_DEPARTMENT_PATH = "/user/{name}/add_department/{departmentId}";
    public final static String DELETE_USER_PATH = "/user/{name}/delete";
    public final static String CHANGE_USER_ID_PATH = "/user/{name}/new_user_id";


}
