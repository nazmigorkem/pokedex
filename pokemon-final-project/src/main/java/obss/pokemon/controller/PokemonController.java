package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.pokemon.PokemonSaveRequest;
import obss.pokemon.model.pokemon.PokemonUpdateRequest;
import obss.pokemon.service.implementation.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pokemon")
@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @PostMapping("/add")
    public ResponseEntity<PokemonResponse> addPokemon(@Valid @RequestBody PokemonSaveRequest pokemonSaveRequest) {
        return ResponseEntity.ok(pokemonService.addPokemon(pokemonSaveRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PokemonResponse>> getPokemonByName(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(pokemonService.getPokemonByNameStartsWithIgnoreCase(name, pageNumber, pageSize));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<PokemonResponse> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByNameIgnoreCase(name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePokemon(@RequestParam String name) {
        pokemonService.deletePokemon(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PokemonResponse> updatePokemon(@Valid @RequestBody PokemonUpdateRequest pokemonUpdateRequest) {
        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonUpdateRequest));
    }

}
