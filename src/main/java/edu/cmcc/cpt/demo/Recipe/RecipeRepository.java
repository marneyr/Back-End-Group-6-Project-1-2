package edu.cmcc.cpt.demo.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Recipe> findAll() {
        String sql = "SELECT recipe_id, user_id, name FROM [recipes]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Recipe.class));
    }

    public Recipe findByRecipeId(int recipe_id) {
        String sql = "SELECT recipe_id, user_id, name FROM [recipes] WHERE recipe_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Recipe.class), recipe_id);
    }

    public Recipe findByUserId(String user_id) {
        String sql = "SELECT recipe_id, user_id, name FROM [recipes] WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Recipe.class), user_id);
    }

    public int save(Recipe recipe) {
        String sql = "INSERT INTO [recipes] (user_id, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, recipe.getUserId(), recipe.getName());
    }

    public int update(int recipe_id, Recipe recipe) {
        String sql = "UPDATE [recipes] SET user_id = ?, name = ? WHERE recipe_id = ?";
        return jdbcTemplate.update(sql, recipe.getUserId(), recipe.getName(), recipe_id);
    }

    public int deleteByRecipeId(int recipe_id) {
        String sql = "DELETE FROM [recipes] WHERE recipe_id = ?";
        return jdbcTemplate.update(sql, recipe_id);
    }
}