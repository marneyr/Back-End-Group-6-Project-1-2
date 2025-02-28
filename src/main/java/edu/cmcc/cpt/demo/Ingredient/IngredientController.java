package edu.cmcc.cpt.demo.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{ingredient_id}")
    public ResponseEntity<Ingredient> getIngredientByIngredientId(@PathVariable int ingredient_id) {
        Ingredient ingredient = ingredientRepository.findByIngredientId(ingredient_id);
        return ingredient != null ? ResponseEntity.ok(ingredient) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return ResponseEntity.ok("Ingredient created successfully with encrypted password.");
    }

    @PutMapping("/{ingredient_id}")
    public ResponseEntity<String> updateIngredient(@PathVariable int ingredient_id, @RequestBody Ingredient ingredient) {
        Ingredient updateIngredient = ingredientRepository.findByIngredientId(ingredient_id); 
        updateIngredient.setRecipeId(ingredient.getRecipeId());
        updateIngredient.setQuantity(ingredient.getQuantity());
        updateIngredient.setIngredientName(ingredient.getIngredientName());
        updateIngredient.setUnit(ingredient.getUnit());

        ingredientRepository.save(updateIngredient);

        return ResponseEntity.ok("Ingredient updated successfully.");

    }

    

    @DeleteMapping("/{ingredient_id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int ingredient_id) {
        int rowsAffected = ingredientRepository.deleteByIngredientId(ingredient_id);
        return rowsAffected > 0 ? ResponseEntity.ok("Ingredient deleted.") : ResponseEntity.notFound().build();
    }
}