package edu.cmcc.cpt.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable int user_id) {
        User user = userRepository.findByUserId(user_id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully with encrypted password.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User submittedUser) {
        User user = userRepository.findByUsername(submittedUser.getUsername());
        if (user != null && passwordEncoder.matches(user.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable int user_id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        int rowsAffected = userRepository.update(user_id, user);
        return rowsAffected > 0 ? ResponseEntity.ok("User updated.") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable int user_id) {
        int rowsAffected = userRepository.deleteByUserId(user_id);
        return rowsAffected > 0 ? ResponseEntity.ok("User deleted.") : ResponseEntity.notFound().build();
    }
}