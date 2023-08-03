package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.PokemonResponseDTO;
import obss.pokemon.model.PokemonSaveRequestDTO;
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

}
