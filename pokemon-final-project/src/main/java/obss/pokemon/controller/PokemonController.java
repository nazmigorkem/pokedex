package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.pokemon.PokemonResponseDTO;
import obss.pokemon.model.pokemon.PokemonSaveRequestDTO;
import obss.pokemon.model.pokemon.PokemonUpdateRequestDTO;
import obss.pokemon.service.implementation.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pokemon")
@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @PostMapping("/add")
    public ResponseEntity<PokemonResponseDTO> addPokemon(@Valid @RequestBody PokemonSaveRequestDTO pokemonSaveRequestDTO) {
        return ResponseEntity.ok(pokemonService.addPokemon(pokemonSaveRequestDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PokemonResponseDTO>> getPokemonByName(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByNameStartsWith(name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePokemon(@RequestParam String name) {
        pokemonService.deletePokemon(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PokemonResponseDTO> updatePokemon(@Valid @RequestBody PokemonUpdateRequestDTO pokemonUpdateRequestDTO) {
        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonUpdateRequestDTO));
    }

}
