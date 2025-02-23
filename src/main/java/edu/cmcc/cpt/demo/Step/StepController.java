package edu.cmcc.cpt.demo.Step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
public class StepController {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Step> getAllSteps() {
        return stepRepository.findAll();
    }

    @GetMapping("/{step_id}")
    public ResponseEntity<Step> getStepByStepId(@PathVariable int step_id) {
        Step step = stepRepository.findByStepId(step_id);
        return step != null ? ResponseEntity.ok(step) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createStep(@RequestBody Step step) {
        step.setPassword(passwordEncoder.encode(step.getPassword())); // Encrypt password
        stepRepository.save(step);
        return ResponseEntity.ok("Step created successfully with encrypted password.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Step submittedUser) {
        Step step = stepRepository.findByUsername(submittedUser.getUsername());
        if (step != null && passwordEncoder.matches(step.getPassword(), step.getPassword())) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    @PutMapping("/{step_id}")
    public ResponseEntity<String> updateStep(@PathVariable int step_id, @RequestBody Step step) {
        step.setPassword(passwordEncoder.encode(step.getPassword())); // Encrypt password
        int rowsAffected = stepRepository.update(step_id, step);
        return rowsAffected > 0 ? ResponseEntity.ok("Step updated.") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{step_id}")
    public ResponseEntity<String> deleteStep(@PathVariable int step_id) {
        int rowsAffected = stepRepository.deleteByStepId(step_id);
        return rowsAffected > 0 ? ResponseEntity.ok("Step deleted.") : ResponseEntity.notFound().build();
    }
}
