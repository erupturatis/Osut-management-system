package javaspring.osutappjava.controller;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.DepartmentDB;
import javaspring.osutappjava.dto.ProjectDB;
import javaspring.osutappjava.dto.user.UserDB;
import javaspring.osutappjava.middleware.UserMiddlewareAuth;
import javaspring.osutappjava.model.ProjectDataModel;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private UserMiddlewareAuth userMiddlewareAuth;

    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private ProjectDataModel projectDataModel;

    @GetMapping(PathsVariables.PROJECT_PATH)
    public String index(@PathVariable String project_id, Model model, HttpServletRequest request) {
        model.addAttribute("project_id", project_id);

        userMiddlewareAuth.checkLogin(request);

        ProjectDB project = projectDataModel.getProject(project_id);
        DepartmentDB department = projectDataModel.getProjectDepartment(project.getDepartment_id());
        List<UserDB> users = projectDataModel.getProjectUsers(project_id);

        model.addAttribute("project", project);
        model.addAttribute("department", department);
        model.addAttribute("users", users);

        // paths passed to html
        model.addAttribute("department_path", PathsVariables.DEPARTMENT_PATH);
        model.addAttribute("project_path", PathsVariables.PROJECT_PATH);
        model.addAttribute("user_path", PathsVariables.USER_MEMBER_PATH);

        return viewVariables.getView(ViewVariables.viewEnum.PROJECT_VIEW);

    }
}
