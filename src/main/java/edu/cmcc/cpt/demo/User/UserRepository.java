package edu.cmcc.cpt.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        String sql = "SELECT user_id, username, email FROM [users]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByUserId(int user_id) {
        String sql = "SELECT user_id, username, email FROM [users] WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), user_id);
    }

    public User findByUsername(String username) {
        String sql = "SELECT user_id, username, email FROM [users] WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    public int save(User user) {
        String sql = "INSERT INTO [users] (email, password) VALUES (?, ?)";
        return jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    public int update(int user_id, User user) {
        String sql = "UPDATE [users] SET email = ?, password = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user_id);
    }

    public int deleteByUserId(int user_id) {
        String sql = "DELETE FROM [users] WHERE user_id = ?";
        return jdbcTemplate.update(sql, user_id);
    }
}