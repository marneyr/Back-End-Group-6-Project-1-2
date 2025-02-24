package edu.cmcc.cpt.demo.Step;

public class Step {
    private int step_id;
    private int recipe_id;
    private int step_order;
    private String step_description;
    private int estimated_time_minutes;

    // Getters and Setters
    public int getStepId() { return step_id; }
    public void setStepId(int step_id) { this.step_id = step_id; }

    public int getRecipeId() { return recipe_id; }
    public void setRecipeId(int recipe_id) { this.recipe_id = recipe_id; }

    public int getStepOrder() { return step_order; }
    public void setStepOrder(int step_order) { this.step_order = step_order; }

    public String getStepDescription() { return step_description; }
    public void setStepDescription(String step_description) { this.step_description = step_description; }

    public int getEstimatedTimeMinutes() { return estimated_time_minutes; }
    public void setEstimatedTimeMinutes(int estimated_time_minutes) { this.estimated_time_minutes = estimated_time_minutes; }

}
