package edu.cmcc.cpt.demo.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        ingredient.setPassword(passwordEncoder.encode(ingredient.getPassword())); // Encrypt password
        ingredientRepository.save(ingredient);
        return ResponseEntity.ok("Ingredient created successfully with encrypted password.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Ingredient submittedIngredient) {
        Ingredient ingredient = ingredientRepository.findByUsername(submittedIngredient.getUsername());
        if (ingredient != null && passwordEncoder.matches(ingredient.getPassword(), ingredient.getPassword())) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    @PutMapping("/{ingredient_id}")
    public ResponseEntity<String> updateIngredient(@PathVariable int ingredient_id, @RequestBody Ingredient ingredient) {
        ingredient.setPassword(passwordEncoder.encode(ingredient.getPassword())); // Encrypt password
        int rowsAffected = ingredientRepository.update(id, ingredient);
        return rowsAffected > 0 ? ResponseEntity.ok("Ingredient updated.") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{ingredient_id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int ingredient_id) {
        int rowsAffected = ingredientRepository.deleteByIngredientId(ingredient_id);
        return rowsAffected > 0 ? ResponseEntity.ok("Ingredient deleted.") : ResponseEntity.notFound().build();
    }
}