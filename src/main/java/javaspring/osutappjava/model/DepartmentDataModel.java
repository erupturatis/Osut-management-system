package javaspring.osutappjava.model;

import javaspring.osutappjava.dto.Department;
import javaspring.osutappjava.dto.Project;
import javaspring.osutappjava.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDataModel {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   public Department getDepartment(String departmentId) {
        String sqlQuery = "SELECT d.* " +
                          "FROM department d " +
                          "WHERE d.department_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{departmentId},
                                               new BeanPropertyRowMapper<>(Department.class));
        } catch (Exception e) {
            System.out.println("Error in fetching department: " + e.getMessage());
            return null;
        }
   }

   public List<Project> getDepartmentProjects(String departmentId) {
        String sqlQuery = "SELECT p.* " +
                          "FROM public.project p " +
                          "WHERE p.department_id = ?;";
        try {
            return jdbcTemplate.query(sqlQuery, new Object[]{departmentId},
                                      new BeanPropertyRowMapper<>(Project.class));
        } catch (Exception e) {
            System.out.println("Error in fetching projects: " + e.getMessage());
            return null;
        }
   }

   public List<User> getDepartmentUsers(String departmentId) {
        String sqlQuery = "SELECT u.* " +
                          "FROM public.user u " +
                          "INNER JOIN user_department ud ON u.user_id = ud.user_id " +
                          "WHERE ud.department_id = ?;";

        try {
            return jdbcTemplate.query(sqlQuery, new Object[]{departmentId},
                                      new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            System.out.println("Error in fetching users: " + e.getMessage());
            return null;
        }
   }

}
