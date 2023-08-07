package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.user.UserPokemonAddRequest;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.model.user.UserSaveRequest;
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

    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsernameIgnoreCase(username));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(userService.addUser(userSaveRequest));
    }

    @GetMapping("/catch-list/{username}")
    public ResponseEntity<Page<PokemonResponse>> getCatchListOfUser(@PathVariable String username, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(userService.getCatchListOfUser(username, pageNumber, pageSize));
    }

    @GetMapping("/wish-list/{username}")
    public ResponseEntity<Page<PokemonResponse>> getWishListOfUser(@PathVariable String username, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(userService.getWishListOfUser(username, pageNumber, pageSize));
    }

    @PostMapping("/catch-list/add")
    public ResponseEntity<UserResponse> addPokemonToCatchListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.addPokemonToCatchListOfUser(userPokemonAddRequest));
    }

    @PreAuthorize("hasRole('TRAINER')")
    @PostMapping("/wish-list/add")
    public ResponseEntity<UserResponse> addPokemonToWishListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.addPokemonToWishListOfUser(userPokemonAddRequest));
    }

}
