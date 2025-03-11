package edu.cmcc.cpt.demo.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Repository
public class RecipeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Recipe> findAll() {
        try {
            String sql = "SELECT recipe_id, user_id, name, cooking_time, difficulty, servings, ingredients, instructions FROM recipes";
            System.out.println("Executing SQL: " + sql);
            
            // First check if there are any recipes at all
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM recipes", Integer.class);
            System.out.println("Total recipes in database: " + count);
            
            // If we have recipes, try to map them
            List<Recipe> recipes = jdbcTemplate.query(sql, new RecipeRowMapper());
            System.out.println("Found " + recipes.size() + " recipes after mapping");
            return recipes;
        } catch (Exception e) {
            System.err.println("Error in findAll: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Return empty list on error
        }
    }

    public Recipe findByRecipeId(int recipe_id) {
        try {
            String sql = "SELECT recipe_id, user_id, name, cooking_time, difficulty, servings, ingredients, instructions FROM recipes WHERE recipe_id = ?";
            return jdbcTemplate.queryForObject(sql, new RecipeRowMapper(), recipe_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Recipe> findByUserId(int user_id) {
        String sql = "SELECT recipe_id, user_id, name, cooking_time, difficulty, servings, ingredients, instructions FROM recipes WHERE user_id = ?";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), user_id);
    }

    public int save(Recipe recipe) {
        try {
            String sql = "INSERT INTO recipes (user_id, name, cooking_time, difficulty, servings, ingredients, instructions) VALUES (?, ?, ?, ?, ?, ?::jsonb, ?::jsonb)";
            
            // Get JSON strings for ingredients and instructions
            String ingredientsJson = recipe.getIngredientsJson();
            String instructionsJson = recipe.getInstructionsJson();
            
            System.out.println("Saving recipe: " + recipe.getName());
            System.out.println("Ingredients JSON: " + ingredientsJson);
            
            return jdbcTemplate.update(sql, 
                recipe.getUserId(), 
                recipe.getName(),
                recipe.getCookingTime(),
                recipe.getDifficulty(),
                recipe.getServings(),
                ingredientsJson,
                instructionsJson
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving recipe", e);
        }
    }

    public int update(int recipe_id, Recipe recipe) {
        try {
            String sql = "UPDATE recipes SET user_id = ?, name = ?, cooking_time = ?, difficulty = ?, servings = ?, ingredients = ?::jsonb, instructions = ?::jsonb WHERE recipe_id = ?";
            
            // Get JSON strings for ingredients and instructions
            String ingredientsJson = recipe.getIngredientsJson();
            String instructionsJson = recipe.getInstructionsJson();
            
            System.out.println("Updating recipe ID: " + recipe_id);
            System.out.println("Ingredients JSON: " + ingredientsJson);
            
            return jdbcTemplate.update(sql, 
                recipe.getUserId(), 
                recipe.getName(),
                recipe.getCookingTime(),
                recipe.getDifficulty(),
                recipe.getServings(),
                ingredientsJson,
                instructionsJson,
                recipe_id
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating recipe", e);
        }
    }

    public int deleteByRecipeId(int recipe_id) {
        String sql = "DELETE FROM recipes WHERE recipe_id = ?";
        return jdbcTemplate.update(sql, recipe_id);
    }
}