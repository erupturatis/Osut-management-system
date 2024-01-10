package javaspring.osutappjava.model;

import javaspring.osutappjava.dto.user.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class UserModel {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDB getUser(String username) {
        try {
            String sql = "SELECT * FROM public.user WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username }, (rs, rowNum) -> {
                UserDB user = new UserDB();
                user.setUser_id(rs.getString("user_id"));
                user.setIs_admin(rs.getBoolean("is_admin"));
                user.setAble_to_work(rs.getBoolean("able_to_work"));
                user.setAge(rs.getInt("age"));

                return user;
            });
        } catch (DataAccessException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return null;
        }
    }
    public UserDB authUser(String username, String password) {
        try {
            String sql = "SELECT * FROM public.user WHERE user_id = ? AND user_password = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, (rs, rowNum) -> {
                UserDB user = new UserDB();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_password(rs.getString("user_password"));
                user.setIs_admin(rs.getBoolean("is_admin"));
                user.setAble_to_work(rs.getBoolean("able_to_work"));
                user.setAge(rs.getInt("age"));

                return user;
            });
        } catch (DataAccessException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return null;
        }
    }

}
