package javaspring.osutappjava.controller;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.dto.user.UserDB;
import javaspring.osutappjava.middleware.UserMiddlewareAuth;
import javaspring.osutappjava.model.DepartmentDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentDataModel departmentDataModel;

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private UserMiddlewareAuth userMiddlewareAuth;

    @Autowired
    private ViewVariables viewVariables;

    @GetMapping(PathsVariables.DEPARTMENT_PATH)
    public String index(@PathVariable String department_id, Model model, HttpServletRequest request) {
        model.addAttribute("department_id", department_id);

        userMiddlewareAuth.checkLogin(request);

        DepartmentDB department = departmentDataModel.getById(department_id);
        List<ProjectDB> projects = departmentDataModel.getDepartmentProjects(department_id);
        List<UserDB> users = departmentDataModel.getDepartmentUsers(department_id);


        model.addAttribute("department", department);
        model.addAttribute("projects", projects);
        model.addAttribute("users", users);

        model.addAttribute("user_path", PathsVariables.USER_MEMBER_PATH);
        model.addAttribute("project_path", PathsVariables.PROJECT_PATH);
        model.addAttribute("department_path", PathsVariables.DEPARTMENT_PATH);

        return viewVariables.getView(ViewVariables.viewEnum.DEPARTMENT_VIEW);

    }
}
