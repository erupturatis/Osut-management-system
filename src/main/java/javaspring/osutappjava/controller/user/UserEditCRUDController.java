package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.model.UserDataModel;
import javaspring.osutappjava.model.service.UserCookieService;
import javaspring.osutappjava.variables.PathsPostVariables;
import javaspring.osutappjava.variables.PathsVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class UserEditCRUDController {

    @Autowired
    private UserDataModel studentDataModel;

    @Autowired
    private UserCookieService userAuthService;

    @Autowired
    private PathsVariables pathsVariables;

    @Autowired
    private PathsPostVariables pathsPostVariables;

    @GetMapping(PathsPostVariables.REMOVE_DEPARTMENT_PATH)
    public String removeDepartment(@PathVariable String name, @PathVariable String departmentId, Model model, HttpServletRequest request) {
        studentDataModel.removeDepartmentFromUser(name, departmentId);
        return pathsVariables.prefixRedirect() + pathsVariables.buildUserMemberEditPath(name);
    }

    @GetMapping(PathsPostVariables.ADD_DEPARTMENT_PATH)
    public String addDepartment(@PathVariable String name, @PathVariable String departmentId, Model model, HttpServletRequest request) {
        studentDataModel.addDepartmentToUser(name, departmentId);
        return pathsVariables.prefixRedirect() + pathsVariables.buildUserMemberEditPath(name);
    }

    @PostMapping(PathsPostVariables.DELETE_USER_PATH)
    public String delete(@PathVariable String name, Model model, HttpServletRequest request) {
        studentDataModel.deleteUser(name);
        return pathsVariables.prefixRedirect() + pathsVariables.USER_ADMIN_PATH;
    }

    @PostMapping(PathsPostVariables.CHANGE_USER_ID_PATH)
    public String changeUserId(@PathVariable String name, @RequestParam("newUsername") String newUsername, Model model, HttpServletRequest request) {
        boolean ok = studentDataModel.changeUserId(name, newUsername);
        if (!ok) {
            return pathsVariables.prefixRedirect() + pathsVariables.buildUserMemberEditPath(name);
        }
        return pathsVariables.prefixRedirect() + pathsVariables.buildUserMemberEditPath(newUsername);
    }

}
