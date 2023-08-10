package obss.pokemon.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import obss.pokemon.model.pokemonType.PokemonTypeResponse;
import obss.pokemon.model.pokemonType.PokemonTypeSaveRequest;
import obss.pokemon.model.pokemonType.PokemonTypeUpdateRequest;
import obss.pokemon.service.implementation.PokemonTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pokemon-type")
@RestController
public class PokemonTypeController {


    private final PokemonTypeService pokemonTypeService;

    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @PostMapping("/add")
    public ResponseEntity<PokemonTypeResponse> addPokemonType(@Valid @RequestBody PokemonTypeSaveRequest pokemonTypeSaveRequest) {
        return ResponseEntity.ok(pokemonTypeService.addPokemonType(pokemonTypeSaveRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PokemonTypeResponse>> getPokemonTypeByNameStartsWith(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(pokemonTypeService.getPokemonTypeByNameStartsWith(name));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<PokemonTypeResponse> getPokemonTypeByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonTypeService.getPokemonTypeByNameIgnoreCaseMapped(name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePokemonType(@NotBlank @RequestParam String name) {
        pokemonTypeService.deletePokemonType(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<PokemonTypeResponse> updatePokemonType(@Valid @RequestBody PokemonTypeUpdateRequest pokemonTypeUpdateRequest) {
        return ResponseEntity.ok(pokemonTypeService.updatePokemonType(pokemonTypeUpdateRequest));
    }
}
