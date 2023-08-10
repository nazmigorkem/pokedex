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

@RequestMapping("/user/list/catch")
@RestController
public class CatchListController {
    private final UserService userService;

    public CatchListController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @GetMapping("/{username}")
    public ResponseEntity<Page<PokemonResponse>> getCatchListOfUser(@PathVariable String username, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(userService.getCatchListOfUser(username, pageNumber, pageSize));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @GetMapping("/is-exists")
    public ResponseEntity<Boolean> isPokemonExistsInCatchListOfUser(@RequestParam String username, @RequestParam String pokemonName) {
        return ResponseEntity.ok(userService.isPokemonInCatchList(username, pokemonName));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userPokemonAddRequest.username == authentication.principal.username")
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addPokemonToCatchListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.addPokemonToCatchListOfUser(userPokemonAddRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #userPokemonAddRequest.username == authentication.principal.username")
    @PostMapping("/delete")
    public ResponseEntity<UserResponse> deletePokemonFromCatchListOfUser(@Valid @RequestBody UserPokemonAddRequest userPokemonAddRequest) {
        return ResponseEntity.ok(userService.deletePokemonFromCatchListOfUser(userPokemonAddRequest));
    }
}
