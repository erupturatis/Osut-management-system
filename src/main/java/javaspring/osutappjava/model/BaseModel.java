package javaspring.osutappjava.model;

import ch.qos.logback.core.status.WarnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public class BaseModel {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    public <T> List<T> getAll() {
        System.out.println("needs implementation");
        return null;
    }

    public <T> T getById(String id) {
        System.out.println("needs implementation");
        return null;
    }

}
