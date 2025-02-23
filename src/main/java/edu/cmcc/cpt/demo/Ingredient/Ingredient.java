package edu.cmcc.cpt.demo.Ingredient;

public class Ingredient {
    private int ingredient_id;
    private int recipe_id;
    private String ingredient_name;
    private String quantity;
    private String unit;

    // Getters and Setters
    public int getIngredientId() { return ingredient_id; }
    public void setIngredientId(int ingredient_id) { this.ingredient_id = ingredient_id; }

    public int getRecipeId() { return recipe_id; }
    public void setRecipeId(int recipe_id) { this.recipe_id = recipe_id; }

    public String getIngredientName() { return ingredient_name; }
    public void setIngredientName(String ingredient_name) { this.ingredient_name = ingredient_name; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
}
