package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.user.UserHeartbeatResponse;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.model.user.UserSaveRequest;
import obss.pokemon.service.implementation.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsernameIgnoreCase(username));
    }

    @GetMapping("/{username}/heartbeat")
    public ResponseEntity<UserHeartbeatResponse> getUserHeartbeatByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserHeartbeatByUsernameIgnoreCase(username));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.addUser(userSaveRequest));
    }


}
