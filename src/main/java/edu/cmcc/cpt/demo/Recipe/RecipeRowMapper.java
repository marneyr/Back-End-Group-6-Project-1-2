package edu.cmcc.cpt.demo.Recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class RecipeRowMapper implements RowMapper<Recipe> {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setUserId(rs.getInt("user_id"));
        recipe.setName(rs.getString("name"));
        
        try {
            recipe.setCookingTime(rs.getString("cooking_time"));
            recipe.setDifficulty(rs.getString("difficulty"));
            recipe.setServings(rs.getInt("servings"));
        } catch (SQLException e) {
            // Optional fields may be null
        }
        
        // Handle PostgreSQL array format: convert {item1,item2} to String[]
        String ingredientsStr = rs.getString("ingredients");
        if (ingredientsStr != null && !ingredientsStr.isEmpty()) {
            try {
                // Handle PostgreSQL array format {item1,item2}
                if (ingredientsStr.startsWith("{") && ingredientsStr.endsWith("}")) {
                    String[] items = ingredientsStr
                        .substring(1, ingredientsStr.length() - 1)
                        .split(",");
                    recipe.setIngredients(items);
                } else {
                    // Try regular JSON parsing as fallback
                    try {
                        recipe.setIngredients(objectMapper.readValue(ingredientsStr, String[].class));
                    } catch (Exception e) {
                        System.err.println("Error parsing ingredients: " + ingredientsStr);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error processing ingredients: " + ingredientsStr);
            }
        }
        
        // Same for instructions
        String instructionsStr = rs.getString("instructions");
        if (instructionsStr != null && !instructionsStr.isEmpty()) {
            try {
                // Handle PostgreSQL array format {item1,item2}
                if (instructionsStr.startsWith("{") && instructionsStr.endsWith("}")) {
                    String[] items = instructionsStr
                        .substring(1, instructionsStr.length() - 1)
                        .split(",");
                    recipe.setInstructions(items);
                } else {
                    // Try regular JSON parsing as fallback
                    try {
                        recipe.setInstructions(objectMapper.readValue(instructionsStr, String[].class));
                    } catch (Exception e) {
                        System.err.println("Error parsing instructions: " + instructionsStr);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error processing instructions: " + instructionsStr);
            }
        }
        
        return recipe;
    }
}