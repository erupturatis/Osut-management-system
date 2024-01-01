package javaspring.osutappjava.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.model.StudentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private StudentDataModel studentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/{name}")
    public String index(@PathVariable String name, Model model, HttpServletRequest request) {
        model.addAttribute("name", name);

        boolean isLoggedIn = false;
        String userType = null; // Could be "admin", "student", etc.

        // Check for a specific cookie, e.g., "loginCookie"
        Cookie[] cookies = request.getCookies();
        UserData userData = null;
        try{
            userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
            userType = userData.getUserType();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // add user type to model
        model.addAttribute("userType", userType);

        if(!isLoggedIn) {
            return "redirect:/login";
        }
        if(!userType.equalsIgnoreCase("admin") && !userData.getUsername().equalsIgnoreCase(name)) {
            return "redirect:/login";
        }

        // handle departments
        List<Department> departments = studentDataModel.getDepartmentsForUser(name);

        model.addAttribute("departments", departments);

        // handle projects
        List<Project> projects = studentDataModel.getProjectsForUser(name);
        List<String> projectsNames = new ArrayList<>();
        for (Project project : projects) {
            projectsNames.add(project.getProject_name());
        }
        model.addAttribute("projects", projectsNames);

        // handle user roles
        List<Role> roles = studentDataModel.getRolesForUser(name);
        List<String> rolesNames = new ArrayList<>();
        for (Role role : roles) {
            rolesNames.add(role.getRole_name());
        }
        model.addAttribute("roles", rolesNames);

        return "home-student";
    }
}
