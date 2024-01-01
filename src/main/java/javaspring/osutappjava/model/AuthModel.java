package javaspring.osutappjava.model;

import javaspring.osutappjava.model.service.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class AuthModel {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public UserTypeEnum authUser(String username, String password) {
        try {
            String sql = "SELECT user_type FROM public.user WHERE user_id = ? AND user_password = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, (rs, rowNum) -> {
                String type = rs.getString("user_type");
                if ("f".equalsIgnoreCase(type)) {
                    return UserTypeEnum.ADMIN;
                } else if ("t".equalsIgnoreCase(type)) {
                    return UserTypeEnum.USER;
                }
                throw new DataAccessException("Something went really wrong in auth") {};
            });
        } catch (DataAccessException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return UserTypeEnum.NONE;
        }
    }

}
