package edu.cmcc.cpt.demo.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        try {
            return recipeRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Return empty list on error
        }
    }

    @GetMapping("/{recipe_id}")
    public ResponseEntity<Recipe> getRecipeByRecipeId(@PathVariable int recipe_id) {
        try {
            System.out.println("RecipeController: Requested recipe ID: " + recipe_id);
            
            Recipe recipe = recipeRepository.findByRecipeId(recipe_id);
            
            if (recipe == null) {
                System.out.println("RecipeController: Recipe with ID " + recipe_id + " not found!");
                return ResponseEntity.notFound().build();
            }
            
            System.out.println("RecipeController: Found recipe - " + recipe.getName() + " (ID: " + recipe.getRecipeId() + ")");
            
            // Ensure proper JSON handling
            if (recipe.getIngredients() instanceof String) {
                String ingredientsStr = (String) recipe.getIngredients();
                // Check if it's already JSON-formatted
                if (ingredientsStr.startsWith("[") && ingredientsStr.endsWith("]")) {
                    // It's already a JSON array string, keep as is
                } else {
                    // Convert to JSON array format
                    recipe.setIngredients("[\"" + ingredientsStr + "\"]");
                }
            }
            
            if (recipe.getInstructions() instanceof String) {
                String instructionsStr = (String) recipe.getInstructions();
                // Check if it's already JSON-formatted
                if (instructionsStr.startsWith("[") && instructionsStr.endsWith("]")) {
                    // It's already a JSON array string, keep as is
                } else {
                    // Convert to JSON array format
                    recipe.setInstructions("[\"" + instructionsStr + "\"]");
                }
            }
            
            return ResponseEntity.ok(recipe);
        } catch (Exception e) {
            System.err.println("RecipeController: Error retrieving recipe with ID " + recipe_id + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        try {
            System.out.println("Received recipe: " + recipe.getName());
            
            Object ingredients = recipe.getIngredients();
            if (ingredients != null) {
                if (ingredients instanceof String[]) {
                    System.out.println("Ingredients: " + Arrays.toString((String[])ingredients));
                } else {
                    System.out.println("Ingredients: " + ingredients.toString());
                }
            } else {
                System.out.println("Ingredients: null");
            }
            
            recipeRepository.save(recipe);
            return ResponseEntity.ok("Recipe created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error creating recipe: " + e.getMessage());
        }
    }

    @PutMapping("/{recipe_id}")
    public ResponseEntity<String> updateRecipe(@PathVariable int recipe_id, @RequestBody Recipe recipe) {
        try {
            Recipe existingRecipe = recipeRepository.findByRecipeId(recipe_id);
            
            if (existingRecipe == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Update name (required field)
            existingRecipe.setName(recipe.getName());
            
            // Update userId (required field)
            existingRecipe.setUserId(recipe.getUserId());
            
            // Update optional fields only if provided in the request
            if (recipe.getCookingTime() != null) {
                existingRecipe.setCookingTime(recipe.getCookingTime());
            }
            
            if (recipe.getDifficulty() != null) {
                existingRecipe.setDifficulty(recipe.getDifficulty());
            }
            
            if (recipe.getServings() > 0) {
                existingRecipe.setServings(recipe.getServings());
            }
            
            if (recipe.getIngredients() != null) {
                existingRecipe.setIngredients(recipe.getIngredients());
            }
            
            if (recipe.getInstructions() != null) {
                existingRecipe.setInstructions(recipe.getInstructions());
            }

            System.out.println("Updating recipe: " + existingRecipe.getName());
            System.out.println("New cooking time: " + existingRecipe.getCookingTime());
            
            try {
                int rowsUpdated = recipeRepository.update(recipe_id, existingRecipe);
                if (rowsUpdated > 0) {
                    return ResponseEntity.ok("Recipe updated successfully.");
                } else {
                    return ResponseEntity.internalServerError().body("No rows updated in database");
                }
            } catch (Exception ex) {
                System.err.println("Error updating recipe: " + ex.getMessage());
                ex.printStackTrace();
                return ResponseEntity.internalServerError().body("Error updating recipe: " + ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error updating recipe: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{recipe_id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int recipe_id) {
        try {
            int rowsAffected = recipeRepository.deleteByRecipeId(recipe_id);
            return rowsAffected > 0 ? ResponseEntity.ok("Recipe deleted.") : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error deleting recipe: " + e.getMessage());
        }
    }
    
    // Simple test endpoint that doesn't interact with the DB
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("RecipeController is working!");
    }
}
