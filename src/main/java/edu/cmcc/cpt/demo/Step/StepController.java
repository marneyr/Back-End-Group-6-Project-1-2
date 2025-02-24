package edu.cmcc.cpt.demo.Step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import edu.cmcc.cpt.demo.Step.Step;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
public class StepController {

    @Autowired
    private StepRepository stepRepository;

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
        stepRepository.save(step);
        return ResponseEntity.ok("Step created successfully.");
    }

    @PutMapping("/{step_id}")
    public ResponseEntity<String> updateStep(@PathVariable int step_id, @RequestBody Step step) {
        Step updateStep = stepRepository.findByStepId(step_id); //.orElseThrow(()-> new ResourceNotFoundException("Step does not exist with id: " + step_id));
        updateStep.setStepId(step.getStepId());
        updateStep.setRecipeId(step.getRecipeId());
        updateStep.setStepOrder(step.getStepOrder());
        updateStep.setStepDescription(step.getStepDescription());
        updateStep.setEstimatedTimeMinutes(step.getEstimatedTimeMinutes());

        stepRepository.save(updateStep);

        return ResponseEntity.ok("Step updated successfully.");
    }

    @DeleteMapping("/{step_id}")
    public ResponseEntity<String> deleteStep(@PathVariable int step_id) {
        int rowsAffected = stepRepository.deleteByStepId(step_id);
        return rowsAffected > 0 ? ResponseEntity.ok("Step deleted.") : ResponseEntity.notFound().build();
    }
}
