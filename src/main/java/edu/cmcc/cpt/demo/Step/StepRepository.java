package edu.cmcc.cpt.demo.Step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StepRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Step> findAll() {
        String sql = "SELECT step_id, recipe_id, step_order, step_description, estimated_time_minutes FROM [recipe_steps]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Step.class));
    }

    public Step findById(int step_id) {
        String sql = "SELECT step_id, first_name, last_name, username FROM [recipe_steps] WHERE step_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Step.class), step_id);
    }

    public Step findByUsername(String username) {
        String sql = "SELECT id, first_name, last_name, username FROM [recipe_steps] WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Step.class), username);
    }

    public int save(Step step) {
        String sql = "INSERT INTO [recipe_steps] (first_name, last_name, password) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, step.getFirstName(), step.getLastName(), step.getPassword());
    }

    public int update(int step_id, Step step) {
        String sql = "UPDATE [recipe_steps] SET first_name = ?, last_name = ?, password = ? WHERE step_id = ?";
        return jdbcTemplate.update(sql, step.getFirstName(), step.getLastName(), step.getPassword(), step_id);
    }

    public int deleteById(int step_id) {
        String sql = "DELETE FROM [recipe_steps] WHERE step_id = ?";
        return jdbcTemplate.update(sql, step_id);
    }
}