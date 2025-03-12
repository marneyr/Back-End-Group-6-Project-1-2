package edu.cmcc.cpt.demo.Recipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Recipe {
    private int recipe_id;
    private int user_id;
    private String name;
    private String cookingTime;
    private String difficulty;
    private int servings;
    private Object ingredients;  // Accept different formats
    private Object instructions; // Accept different formats

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Getters and setters
    @JsonProperty("recipeId")
    public int getRecipeId() { return recipe_id; }
    
    @JsonProperty("recipeId")
    public void setRecipeId(int recipe_id) { this.recipe_id = recipe_id; }

    @JsonProperty("userId")
    public int getUserId() { return user_id; }
    
    @JsonProperty("userId")
    public void setUserId(int user_id) { this.user_id = user_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCookingTime() { return cookingTime; }
    public void setCookingTime(String cookingTime) { this.cookingTime = cookingTime; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }

    // Handle different formats for ingredients
    public Object getIngredients() { return ingredients; }
    public void setIngredients(Object ingredients) { this.ingredients = ingredients; }

    // Handle different formats for instructions
    public Object getInstructions() { return instructions; }
    public void setInstructions(Object instructions) { this.instructions = instructions; }

    // Helper method for converting ingredients to JSON string
    @JsonIgnore
    public String getIngredientsJson() throws IOException {
        if (ingredients == null) {
            return null;
        }
        if (ingredients instanceof String) {
            return (String) ingredients;
        }
        return objectMapper.writeValueAsString(ingredients);
    }

    // Helper method for converting instructions to JSON string
    @JsonIgnore
    public String getInstructionsJson() throws IOException {
        if (instructions == null) {
            return null;
        }
        if (instructions instanceof String) {
            return (String) instructions;
        }
        return objectMapper.writeValueAsString(instructions);
    }
    
    // Add toString method for better logging
    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipe_id +
                ", name='" + name + '\'' +
                ", cookingTime='" + cookingTime + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", servings=" + servings +
                '}';
    }
}
