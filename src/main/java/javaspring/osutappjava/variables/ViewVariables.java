package javaspring.osutappjava.variables;

import org.springframework.stereotype.Component;

@Component
public class ViewVariables {
    public enum viewEnum {
        AUTH_VIEW,
        USER_ADMIN_VIEW,
        USER_MEMBER_VIEW,
        USER_EDIT_VIEW,
        USER_CREATE_VIEW,
        DEPARTMENT_VIEW,
        PROJECT_VIEW


    }

    public String AUTH_VIEW = "auth-view";
    public String USER_ADMIN_VIEW = "user-admin-view";
    public String USER_MEMBER_VIEW = "user-member-view";
    public String USER_EDIT_VIEW = "user-edit-view";
    public String USER_CREATE_VIEW = "user-create-view";
    public String DEPARTMENT_VIEW = "department-view";
    public String PROJECT_VIEW = "project-view";

    public String getView(viewEnum userViewEnum) {
        if (userViewEnum == viewEnum.AUTH_VIEW) {
            return AUTH_VIEW;
        }
        if (userViewEnum == viewEnum.USER_ADMIN_VIEW) {
            return USER_ADMIN_VIEW;
        }
        if (userViewEnum == viewEnum.USER_MEMBER_VIEW) {
            return USER_MEMBER_VIEW;
        }
        if (userViewEnum == viewEnum.USER_EDIT_VIEW) {
            return USER_EDIT_VIEW;
        }
        if (userViewEnum == viewEnum.USER_CREATE_VIEW) {
            return USER_CREATE_VIEW;
        }
        if (userViewEnum == viewEnum.DEPARTMENT_VIEW) {
            return DEPARTMENT_VIEW;
        }
        if (userViewEnum == viewEnum.PROJECT_VIEW) {
            return PROJECT_VIEW;
        }
        throw new RuntimeException("No view found");
    }


}
