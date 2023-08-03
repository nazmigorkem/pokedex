package obss.pokemon.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import obss.pokemon.model.PokemonTypeResponseDTO;
import obss.pokemon.model.PokemonTypeSaveRequestDTO;
import obss.pokemon.model.PokemonTypeUpdateRequestDTO;
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
    public ResponseEntity<PokemonTypeResponseDTO> addPokemonType(@Valid @RequestBody PokemonTypeSaveRequestDTO pokemonTypeSaveRequest) {
        return ResponseEntity.ok(pokemonTypeService.addPokemonType(pokemonTypeSaveRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PokemonTypeResponseDTO>> getPokemonTypeByName(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(pokemonTypeService.getPokemonTypeByNameStartsWith(name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePokemonType(@RequestParam @NotBlank String name) {
        pokemonTypeService.deletePokemonType(name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<PokemonTypeResponseDTO> updatePokemonType(@Valid @RequestBody PokemonTypeUpdateRequestDTO pokemonTypeUpdateRequestDTO) {
        return ResponseEntity.ok(pokemonTypeService.updatePokemonType(pokemonTypeUpdateRequestDTO));
    }
}
