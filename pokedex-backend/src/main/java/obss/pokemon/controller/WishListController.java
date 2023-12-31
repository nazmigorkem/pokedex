package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.user.UserPokemonAddRequest;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.service.implementation.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/user/list/wish")
@RestController
public class WishListController {
    private final UserService userService;

    public WishListController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @GetMapping("/{username}")
    public ResponseEntity<Page<PokemonResponse>> getWishListOfUser(@PathVariable String username, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(userService.getWishListOfUser(username, pageNumber, pageSize));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @GetMapping("/is-exists")
    public ResponseEntity<Boolean> isPokemonExistsInWishListOfUser(@RequestParam String username, @RequestParam String pokemonName) {
        return ResponseEntity.ok(userService.isPokemonInWishList(username, pokemonName));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userPokemonAddRequest.username == authentication.principal.username")
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addPokemonToWishListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.addPokemonToWishListOfUser(userPokemonAddRequest));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userPokemonAddRequest.username == authentication.principal.username")
    @PostMapping("/delete")
    public ResponseEntity<UserResponse> deletePokemonFromWishListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.deletePokemonFromWishListOfUser(userPokemonAddRequest));
    }
}
