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
        return recipeRepository.findAll();
    }

    @GetMapping("/{recipe_id}")
    public ResponseEntity<Recipe> getRecipeByRecipeId(@PathVariable int recipe_id) {
        Recipe recipe = recipeRepository.findByRecipeId(recipe_id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        recipeRepository.save(recipe);
        return ResponseEntity.ok("Recipe created successfully.");
    }

    @PutMapping("/{recipe_id}")
    public ResponseEntity<String> updateRecipe(@PathVariable int recipe_id, @RequestBody Recipe recipe) {
        Recipe updateRecipe = recipeRepository.findByRecipeId(recipe_id); 
        updateRecipe.setRecipeId(recipe.getRecipeId());
        updateRecipe.setUserId(recipe.getRecipeId());
        updateRecipe.setName(recipe.getName());

        recipeRepository.save(updateRecipe);

        return ResponseEntity.ok("Recipe updated successfully.");
    }
    
    @DeleteMapping("/{recipe_id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int recipe_id) {
        int rowsAffected = recipeRepository.deleteByRecipeId(recipe_id);
        return rowsAffected > 0 ? ResponseEntity.ok("Recipe deleted.") : ResponseEntity.notFound().build();
    }
}
