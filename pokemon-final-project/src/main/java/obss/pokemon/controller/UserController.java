package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.user.UserHeartbeatResponse;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.model.user.UserSaveRequest;
import obss.pokemon.model.user.UserSearchResponse;
import obss.pokemon.service.implementation.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsernameIgnoreCase(username));
    }

    @GetMapping("/search/{username}/heartbeat")
    public ResponseEntity<UserHeartbeatResponse> getUserHeartbeatByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserHeartbeatByUsernameIgnoreCase(username));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<UserSearchResponse>> getUserByUsernameStartsWith(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(userService.getUserByUsernameStartsWithIgnoreCase(name, pageNumber, pageSize));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.addUser(userSaveRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsernameIgnoreCase(username);
        return ResponseEntity.ok().build();
    }


}
