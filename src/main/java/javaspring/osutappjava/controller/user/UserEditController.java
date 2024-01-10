package javaspring.osutappjava.controller.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.model.StudentDataModel;
import javaspring.osutappjava.model.service.UserAuthService;
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
    private StudentDataModel studentDataModel;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/{name}/edit")
    public String index(@PathVariable String name, Model model, HttpServletRequest request) {

        boolean isLoggedIn = false;
        String userType = null;

        Cookie[] cookies = request.getCookies();
        UserData userData = null;
        try{
            userData = userAuthService.searchForLoginCookie(cookies);
            isLoggedIn = true;
            userType = userData.getUserType();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if(!isLoggedIn) {
            return "redirect:/login";
        }
        if(!userType.equalsIgnoreCase("admin")) {
            return "redirect:/login";
        }

        if(!studentDataModel.checkUserExists(name)) {
            return "redirect:/admin";
        }

        List<DepartmentDB> departments = studentDataModel.getDepartmentsForUser(name);
        List<ProjectDB> projects = studentDataModel.getProjectsForUser(name);
        List<DepartmentDB> allDepartments = studentDataModel.getDepartments();
        List<ProjectDB> allProjects = studentDataModel.getProjects();

        List<DepartmentDB> nonJoinedDepartments = new ArrayList<>();
        List<ProjectDB> nonJoinedProjects = new ArrayList<>();

        for(DepartmentDB department : allDepartments) {
            boolean joined = false;
            for(DepartmentDB userDepartment : departments) {
                if(department.getDepartment_id().equalsIgnoreCase(userDepartment.getDepartment_id())) {
                    joined = true;
                    break;
                }
            }
            if(!joined) {
                nonJoinedDepartments.add(department);
            }
        }

        for(ProjectDB project : allProjects) {
            boolean joined = false;
            for(ProjectDB userProject : projects) {
                if(project.getProject_id().equalsIgnoreCase(userProject.getProject_id())) {
                    joined = true;
                    break;
                }
            }
            if(!joined) {
                nonJoinedProjects.add(project);
            }
        }

        model.addAttribute("name", name);
        model.addAttribute("joinedDepartments", departments);
        model.addAttribute("joinedProjects", projects);

        model.addAttribute("nonJoinedDepartments", nonJoinedDepartments);
        model.addAttribute("nonJoinedProjects", nonJoinedProjects);

        return "user-edit-view";
    }

}
