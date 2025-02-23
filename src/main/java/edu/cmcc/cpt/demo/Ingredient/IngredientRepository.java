package edu.cmcc.cpt.demo.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Ingredient> findAll() {
        String sql = "SELECT ingredient_id, recipe_id, ingredient_name, quantity, unit FROM [recipe_ingredients]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ingredient.class));
    }

    public Ingredient findByIngredientId(int ingredient_id) {
        String sql = "SELECT ingredient_id, recipe_id, ingredient_name, quantity, unit FROM [recipe_ingredients] WHERE ingredient_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ingredient.class), ingredient_id);
    }

    public Ingredient findByRecipeId(int recipe_id) {
        String sql = "SELECT ingredient_id, recipe_id, ingredient_name, quantity, unit FROM [recipe_ingredients] WHERE recipe_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ingredient.class), recipe_id);
    }

    public int save(Ingredient ingredient) {
        String sql = "INSERT INTO [recipe_ingredients] (ingredient_name, quantity, unit) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, ingredient.getIngredientName(), ingredient.getQuantity(), ingredient.getUnit());
    }

    public int update(int ingredient_id, Ingredient ingredient) {
        String sql = "UPDATE [recipe_ingredients] SET ingredient_name = ?, quantity = ?, unit = ? WHERE ingredient_id = ?";
        return jdbcTemplate.update(sql, ingredient.getIngredientName(), ingredient.getQuantity(), ingredient.getUnit(), ingredient_id);
    }

    public int deleteByIngredientId(int ingredient_id) {
        String sql = "DELETE FROM [recipe_ingredients] WHERE ingredient_id = ?";
        return jdbcTemplate.update(sql, ingredient_id);
    }
}