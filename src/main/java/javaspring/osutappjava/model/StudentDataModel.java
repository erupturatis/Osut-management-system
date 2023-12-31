package javaspring.osutappjava.model;

import javaspring.osutappjava.dto.*;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class StudentDataModel {

   @Autowired
   private JdbcTemplate jdbcTemplate;


   public List<Department> getDepartments() {
       String sqlQuery = "SELECT * FROM department;";
       return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Department.class));
   }


   public List<User> getUsers() {
        String sqlQuery = "SELECT * FROM public.user;";
        return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(User.class));
   }

    public List<Project> getProjects() {
          String sqlQuery = "SELECT * FROM public.project;";
          return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(Project.class));
    }

    public boolean checkUserExists(String userId) {
        String sqlQuery = "SELECT COUNT(*) FROM public.user WHERE user_id = ?;";
        try {
            int count = jdbcTemplate.queryForObject(sqlQuery, new Object[]{userId}, Integer.class);
            return count > 0;
        } catch (Exception e) {
            System.out.println("Error in checking if user exists: " + e.getMessage());
            return false;
        }
    }


    public List<User> getUsers(Optional<String> filter, boolean sortByAttendance) {
        // Base SQL query
        String sqlQuery = "SELECT u.*, COUNT(ua.attendance_id) as attendance_count " +
                "FROM public.user u " +
                "LEFT JOIN public.user_attendance ua ON u.user_id = ua.user_id ";

        // If a department filter is provided, join with the user_department table and filter by department
        List<Object> params = new ArrayList<>();
        if (filter.isPresent() && !filter.get().isEmpty()) {
            sqlQuery += "JOIN public.user_department ud ON u.user_id = ud.user_id " +
                    "WHERE ud.department_id = ?";
            params.add(filter.get());
        }

        // Group by user to prepare for counting attendances
        sqlQuery += " GROUP BY u.user_id ";

        // If sorting by attendance is requested, add an ORDER BY clause
        if (sortByAttendance) {
            sqlQuery += "ORDER BY attendance_count DESC"; // Assuming you want in descending order
        }

        // Execute the SQL query with the appropriate parameters based on whether a filter was provided
        if (!params.isEmpty()) {
            return jdbcTemplate.query(sqlQuery, params.toArray(), new BeanPropertyRowMapper<>(User.class));
        } else {
            return jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(User.class));
        }
    }

    public List<User> getUsersForDepartment(String departmentId) {
        String sqlQuery = "SELECT u.* " +
                "FROM public.user u " +
                "JOIN user_department ud ON u.user_id = ud.user_id " +
                "JOIN department d ON ud.department_id = d.department_id " +
                "WHERE d.department_id = ?;";

        return jdbcTemplate.query(sqlQuery, new Object[]{departmentId},
                new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getUsersSortedByAttendanceNumber() {
        String sqlQuery = "SELECT u.* " +
                "FROM public.user u " +
                "ORDER BY u.attendance_number;";

        return jdbcTemplate.query(sqlQuery,
                new BeanPropertyRowMapper<>(User.class));
    }

    public List<Department> getDepartmentsForUser(String userId) {
       String sqlQuery = "SELECT d.* " +
               "FROM department d " +
               "JOIN user_department ud ON d.department_id = ud.department_id " +
               "JOIN public.user u ON ud.user_id = u.user_id " +
               "WHERE ud.user_id = ?;";

        return jdbcTemplate.query(sqlQuery, new Object[]{userId},
                new BeanPropertyRowMapper<>(Department.class));
    }
    public List<Project> getProjectsForUser(String userId) {
        String sqlQuery = "SELECT p.* " +
                "FROM project p " +
                "JOIN user_project up ON p.project_id = up.project_id " +
                "JOIN public.user u ON up.user_id = u.user_id " +
                "WHERE up.user_id = ?;";

        return jdbcTemplate.query(sqlQuery, new Object[]{userId},
                new BeanPropertyRowMapper<>(Project.class));
    }

    public List<Role> getRolesForUser(String userId) {
        String sqlQuery = "SELECT r.* " +
                "FROM role r " +
                "JOIN user_role ur ON r.role_id = ur.role_id " +
                "JOIN public.user u ON ur.user_id = u.user_id " +
                "WHERE ur.user_id = ?;";

        return jdbcTemplate.query(sqlQuery, new Object[]{userId},
                new BeanPropertyRowMapper<>(Role.class));
    }

    public boolean addUser(UserDB user){
        String sqlQuery = "INSERT INTO public.user (user_id, user_password, user_type) VALUES (?, ?, ?);";
        try {
            jdbcTemplate.update(sqlQuery, user.getUser_id(), user.getUser_password(), user.getUser_type());
            return true;
        } catch (Exception e) {
            System.out.println("Error in adding user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String userId) {
        String sqlQuery = "DELETE FROM public.user WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, userId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in deleting user: " + e.getMessage());
            return false;
        }
    }

    public boolean removeDepartmentFromUser(String userId, String departmentId) {
        String sqlQuery = "DELETE FROM user_department WHERE user_id = ? AND department_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, userId, departmentId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in removing department from user: " + e.getMessage());
            return false;
        }
    }

    public boolean removeProjectFromUser(String userId, String projectId) {
        String sqlQuery = "DELETE FROM user_project WHERE user_id = ? AND project_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, userId, projectId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in removing project from user: " + e.getMessage());
            return false;
        }
    }

    public boolean addDepartmentToUser(String userId, String departmentId) {
        String sqlQuery = "INSERT INTO user_department (user_id, department_id) VALUES (?, ?);";
        try {
            jdbcTemplate.update(sqlQuery, userId, departmentId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in adding department to user: " + e.getMessage());
            return false;
        }
    }

    public boolean addProjectToUser(String userId, String projectId) {
        String sqlQuery = "INSERT INTO user_project (user_id, project_id) VALUES (?, ?);";
        try {
            jdbcTemplate.update(sqlQuery, userId, projectId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in adding project to user: " + e.getMessage());
            return false;
        }
    }

    public boolean changeUserId(String userId, String newUserId) {
        String sqlQuery = "UPDATE public.user SET user_id = ? WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, newUserId, userId);
            return true;
        } catch (Exception e) {
            System.out.println("Error in changing user id: " + e.getMessage());
            return false;
        }
    }

}
