package edu.cmcc.cpt.demo.Recipe;

public class Recipe {
    private int recipe_id;
    private int user_id;
    private String name;
    private String ingredients;    // Store as JSON array string
    private String instructions;   // Store as JSON array string

    // Getters and Setters
    public int getRecipeId() { return recipe_id; }
    public void setRecipeId(int recipe_id) { this.recipe_id = recipe_id; }

    public int getUserId() { return user_id; }
    public void setUserId(int user_id) { this.user_id = user_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
