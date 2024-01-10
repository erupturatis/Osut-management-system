package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.middleware.UserMiddlewareAuth;
import javaspring.osutappjava.model.DepartmentDataModel;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.PathsVariables;
import javaspring.osutappjava.variables.ViewVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class UserEditController {

    @Autowired
    private UserDataModel studentDataModel;

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private DepartmentDataModel departmentDataModel;

    @Autowired
    private UserMiddlewareAuth userMiddlewareAuth;

    @Autowired
    private ViewVariables viewVariables;

    @Autowired
    private PathsVariables pathsVariables;

    @GetMapping(PathsVariables.USER_EDIT_PATH)
    public String index(@PathVariable String name, Model model, HttpServletRequest request) {

        String redirect = userMiddlewareAuth.checkLoginAndOwner(request, name);
        if (redirect != null) {
            return redirect;
        }

        redirect = userMiddlewareAuth.checkUserExists(name);
        if (redirect != null) {
            return redirect;
        }

        boolean isAdmin = userMiddlewareAuth.checkUserIsAdmin(request);

        List<DepartmentDB> departments = studentDataModel.getDepartmentsForUser(name);
        List<ProjectDB> projects = studentDataModel.getProjectsForUser(name);
        List<DepartmentDB> allDepartments = departmentDataModel.getDepartments();

        List<DepartmentDB> nonJoinedDepartments = new ArrayList<>();
        for (DepartmentDB department : allDepartments) {
            boolean joined = false;
            for (DepartmentDB userDepartment : departments) {
                if (department.getDepartment_id().equalsIgnoreCase(userDepartment.getDepartment_id())) {
                    joined = true;
                    break;
                }
            }
            if (!joined) {
                nonJoinedDepartments.add(department);
            }
        }

        model.addAttribute("name", name);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("joinedDepartments", departments);
        model.addAttribute("nonJoinedDepartments", nonJoinedDepartments);

        return viewVariables.getView(ViewVariables.viewEnum.USER_EDIT_VIEW);
    }

}
