package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.dto.user.UserCookieData;
import javaspring.osutappjava.variables.UserVariables;
import javaspring.osutappjava.model.StudentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private StudentDataModel studentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserVariables userVariables;


    @GetMapping("/{name}")
    public String index(@PathVariable String name, Model model, HttpServletRequest request) {
        model.addAttribute("name", name);

        boolean isLoggedIn = false;
        boolean userIsAdmin = false;

        // check for credentials saved in cookie
        Cookie[] cookies = request.getCookies();
        UserCookieData userData = null;
        try{
            userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
            userIsAdmin = userData.getIsAdmin();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        model.addAttribute(userVariables.USER_IS_ADMIN, userIsAdmin);

        // not logged in
        if(!isLoggedIn) {
            return "redirect:/login";
        }

        // not admin, nor owner of page
        if(!userIsAdmin && !userData.getUsername().equalsIgnoreCase(name)) {
            return "redirect:/login";
        }

        // handle departments
        List<DepartmentDB> departments = studentDataModel.getDepartmentsForUser(name);
        model.addAttribute("departments", departments);

        // handle projects
        List<ProjectDB> projects = studentDataModel.getProjectsForUser(name);
        model.addAttribute("projects", projects);

        return "user-member-view";
    }
}
