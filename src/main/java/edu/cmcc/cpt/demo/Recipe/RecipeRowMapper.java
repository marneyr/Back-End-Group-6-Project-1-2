package edu.cmcc.cpt.demo.Recipe;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecipeRowMapper implements RowMapper<Recipe> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setUserId(rs.getInt("user_id"));
        recipe.setName(rs.getString("name"));
        
        // Handle optional fields
        recipe.setCookingTime(rs.getString("cooking_time"));
        recipe.setDifficulty(rs.getString("difficulty"));
        recipe.setServings(rs.getInt("servings"));
        
        // Get the raw JSON string for ingredients and instructions
        String ingredientsJson = rs.getString("ingredients");
        String instructionsJson = rs.getString("instructions");
        
        // Try to parse JSON strings to objects
        if (ingredientsJson != null) {
            try {
                recipe.setIngredients(ingredientsJson);
            } catch (Exception e) {
                System.err.println("Error parsing ingredients JSON: " + e.getMessage());
                recipe.setIngredients(ingredientsJson);
            }
        }
        
        if (instructionsJson != null) {
            try {
                recipe.setInstructions(instructionsJson);
            } catch (Exception e) {
                System.err.println("Error parsing instructions JSON: " + e.getMessage());
                recipe.setInstructions(instructionsJson);
            }
        }
        
        return recipe;
    }
}