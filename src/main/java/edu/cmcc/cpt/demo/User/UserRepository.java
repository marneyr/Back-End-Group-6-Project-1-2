package edu.cmcc.cpt.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        String sql = "SELECT user_id, username, email, password FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByUserId(int user_id) {
        try {
            String sql = "SELECT user_id, username, email, password FROM users WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findByUsername(String username) {
        try {
            String sql = "SELECT user_id, username, email, password FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int save(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword());
    }

    public int update(int user_id, User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user_id);
    }

    public int deleteByUserId(int user_id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbcTemplate.update(sql, user_id);
    }
}