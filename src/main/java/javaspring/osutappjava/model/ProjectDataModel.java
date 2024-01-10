package javaspring.osutappjava.model;

import javaspring.osutappjava.dto.ProjectDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import javaspring.osutappjava.dto.*;
import javaspring.osutappjava.dto.user.UserDB;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@Component
public class ProjectDataModel {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProjectDB getProject(String project_id) {
        try {
            String sql = "SELECT * FROM public.project WHERE project_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{project_id}, (rs, rowNum) -> {
                ProjectDB project = new ProjectDB();
                project.setProject_id(rs.getString("project_id"));
                project.setProject_name(rs.getString("project_name"));
                project.setProject_description(rs.getString("project_description"));
                project.setProject_funding(rs.getInt("project_funding"));
                project.setDepartment_id(rs.getString("department_id"));

                return project;
            });
        } catch (DataAccessException e) {
            System.out.println("Get project failed: " + e.getMessage());
            return null;
        }
    }

    public List<UserDB> getProjectUsers(String project_id) {
        String sqlQuery = "SELECT u.* FROM public.user u " +
                "JOIN public.user_project up ON u.user_id = up.user_id " +
                "WHERE up.project_id = ?;";
        return jdbcTemplate.query(sqlQuery, new Object[]{project_id}, new BeanPropertyRowMapper<>(UserDB.class));
    }

    public DepartmentDB getProjectDepartment(String department_id) {
        try {
            String sql = "SELECT * FROM public.department WHERE department_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{department_id}, (rs, rowNum) -> {
                DepartmentDB department = new DepartmentDB();
                department.setDepartment_id(rs.getString("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                department.setDepartment_description(rs.getString("department_description"));

                return department;
            });
        } catch (DataAccessException e) {
            System.out.println("Get department failed: " + e.getMessage());
            return null;
        }
    }
}
