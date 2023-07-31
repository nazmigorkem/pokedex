package tech.obss.controller;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public String saveUser(@PathVariable("id") long id) {
        return "Save user:" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return "Delete user: " + id;
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") long id) {
        return "Update user: " + id;
    }
}
