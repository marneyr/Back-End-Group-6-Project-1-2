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

    public Step findByStepId(int step_id) {
        String sql = "SELECT step_id, recipe_id, step_order, step_description, estimated_time_minutes FROM [recipe_steps] WHERE step_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Step.class), step_id);
    }

    public Step findByRecipeId(String recipe_id) {
        String sql = "SELECT step_id, recipe_id, step_oder, step_description, estimated_time_minutes FROM [recipe_steps] WHERE recipe_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Step.class), recipe_id);
    }

    public int save(Step step) {
        String sql = "INSERT INTO [recipe_steps] (recipe_id, step_order, step_description, estimated_time_minutes) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, step.getRecipeId(), step.getStepOrder(), step.getStepDescription(), step.getEstimatedTimeMinutes());
    }

    public int update(int step_id, Step step) {
        String sql = "UPDATE [recipe_steps] SET recipe_id = ?, step_order = ?, step_description = ?, estimated_time_minutes = ? WHERE step_id = ?";
        return jdbcTemplate.update(sql, step.getRecipeId(), step.getStepOrder(), step.getStepDescription(), step.getEstimatedTimeMinutes(), step_id);
    }

    public int deleteByStepId(int step_id) {
        String sql = "DELETE FROM [recipe_steps] WHERE step_id = ?";
        return jdbcTemplate.update(sql, step_id);
    }
}