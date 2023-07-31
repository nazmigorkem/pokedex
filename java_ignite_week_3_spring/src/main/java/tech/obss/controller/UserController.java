package tech.obss.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<String> getUsers() {
        return ResponseEntity.ok("Get users");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUsers(@PathVariable("id") long id) {
        return ResponseEntity.ok("Get user: " + id);
    }

    @PostMapping("")
    public SaveUserRequestDTO saveUser(@Valid @RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        LOGGER.info("Save user request: {}", saveUserRequestDTO);
        return saveUserRequestDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok("Delete user: " + id);
    }

    @PutMapping("/{id}")
    public UpdateUserRequestDTO updateUser(@PathVariable("id") long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return updateUserRequestDTO;
    }
}
