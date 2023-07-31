package tech.obss.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("")
    public String getUsers() {
        return "Get users";
    }

    @GetMapping("/{id}")
    public String getUsers(@PathVariable("id") long id) {
        return "Get user: " + id;
    }

    @PostMapping("")
    public SaveUserRequestDTO saveUser(@Valid @RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        return saveUserRequestDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return "Delete user: " + id;
    }

    @PutMapping("/{id}")
    public UpdateUserRequestDTO updateUser(@PathVariable("id") long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return updateUserRequestDTO;
    }
}
