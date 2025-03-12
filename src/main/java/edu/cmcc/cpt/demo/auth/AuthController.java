package edu.cmcc.cpt.demo.auth;

import edu.cmcc.cpt.demo.User.User;
import edu.cmcc.cpt.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {
        // Validate request
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
            request.getPassword() == null || request.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        
        // Check if user already exists
        if (userRepository.findByUsername(request.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
        
        try {
            // Create new user
            User newUser = new User();
            newUser.setUsername(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            
            // Save user
            userRepository.save(newUser);
            
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error during registration: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        // Validate request
        if (request.getEmail() == null || request.getEmail().isEmpty() ||
            request.getPassword() == null || request.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        
        // Find user by username
        User user = userRepository.findByUsername(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        
        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        
        // In a real app, you would generate and return a JWT token here
        return ResponseEntity.ok("Login successful");
    }
}
