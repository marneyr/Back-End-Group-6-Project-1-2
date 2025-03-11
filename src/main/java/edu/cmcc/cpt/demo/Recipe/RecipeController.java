package edu.cmcc.cpt.demo.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            Recipe recipe = recipeRepository.findByRecipeId(recipe_id);
            return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        try {
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
            
            // Update the existing recipe with new values
            existingRecipe.setName(recipe.getName());
            existingRecipe.setUserId(recipe.getUserId());

            try {
                recipeRepository.update(recipe_id, existingRecipe);
            } catch (Exception ex) {
                recipeRepository.save(existingRecipe);
            }

            return ResponseEntity.ok("Recipe updated successfully.");
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
