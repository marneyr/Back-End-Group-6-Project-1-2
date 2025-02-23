package edu.cmcc.cpt.demo.Recipe;

public class Recipe {
    private int recipe_id;
    private int user_id;
    private String name;
    private String lastName;
    private String username; ///create at time
    private String password;

    // Getters and Setters
    public int getRecipeId() { return recipe_id; }
    public void setRecipeId(int recipe_id) { this.recipe_id = recipe_id; }

    public int getUserId() { return user_id; }
    public void setUserId(int user_id) { this.user_id = user_id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
