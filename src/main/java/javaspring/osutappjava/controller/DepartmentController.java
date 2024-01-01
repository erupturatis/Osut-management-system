package javaspring.osutappjava.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.model.DepartmentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentDataModel departmentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/department/{department_id}")
    public String index(@PathVariable String department_id, Model model, HttpServletRequest request) {
        model.addAttribute("department_id", department_id);

        boolean isLoggedIn = false;
        String userType = null;
        Cookie[] cookies = request.getCookies();
        try{
            UserData userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/login";
        }

        if(!isLoggedIn) {
            return "redirect:/login";
        }
        // if logged in, finds department data
        Department department = departmentDataModel.getDepartment(department_id);
        List<Project> projects = departmentDataModel.getDepartmentProjects(department_id);
        List<User> users = departmentDataModel.getDepartmentUsers(department_id);

        List<String> projectNames = new ArrayList<>();
        List<String> projectDescriptions = new ArrayList<>();

        for (Project project : projects) {
            projectNames.add(project.getProject_name());
            projectDescriptions.add(project.getProject_description());
        }

        List<String> userNames = new ArrayList<>();
        for (User user : users) {
            userNames.add(user.getUser_id());
        }

        model.addAttribute("department_name", department.getDepartment_name());
        model.addAttribute("department_description", department.getDepartment_description());
        model.addAttribute("project_names", projectNames);
        model.addAttribute("project_descriptions", projectDescriptions);
        model.addAttribute("user_names", userNames);

        return "department";

    }
}
