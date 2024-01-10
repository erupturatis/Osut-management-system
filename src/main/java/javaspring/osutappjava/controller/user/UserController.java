package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.middleware.UserMiddlewareAuth;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.UserVariables;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDataModel studentDataModel;

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private UserVariables userVariables;

    @Autowired
    private UserMiddlewareAuth userMiddlewareAuth;

    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private PathsVariables pathsVariables;

    @GetMapping(PathsVariables.USER_MEMBER_PATH)
    public String index(@PathVariable String name, Model model, HttpServletRequest request) {
        model.addAttribute("name", name);

        String redirect = userMiddlewareAuth.checkLoginAndOwner(request, name);
        if (redirect != null) {
            return redirect;
        }

        redirect = userMiddlewareAuth.checkUserExists(name);
        if (redirect != null) {
            return redirect;
        }

        boolean isAdmin = userMiddlewareAuth.checkUserIsAdmin(request);

        // handle departments
        List<DepartmentDB> departments = studentDataModel.getDepartmentsForUser(name);
        model.addAttribute("departments", departments);

        // handle projects
        List<ProjectDB> projects = studentDataModel.getProjectsForUser(name);
        model.addAttribute("projects", projects);
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("user_edit_path", PathsVariables.USER_EDIT_PATH);
        model.addAttribute("department_path", PathsVariables.DEPARTMENT_PATH);
        model.addAttribute("project_path", PathsVariables.PROJECT_PATH);

        return viewVariables.getView(ViewVariables.viewEnum.USER_MEMBER_VIEW);
    }

}
