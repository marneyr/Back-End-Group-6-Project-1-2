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
        String sql = "SELECT id, first_name, last_name, username FROM [user]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(int id) {
        String sql = "SELECT id, first_name, last_name, username FROM [user] WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public User findByUsername(String username) {
        String sql = "SELECT id, first_name, last_name, username FROM [user] WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    public int save(User user) {
        String sql = "INSERT INTO [user] (first_name, last_name, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getPassword());
    }

    public int update(int id, User user) {
        String sql = "UPDATE [user] SET first_name = ?, last_name = ?, password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getPassword(), id);
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM [user] WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}