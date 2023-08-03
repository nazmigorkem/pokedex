package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.PokemonTypeResponseDTO;
import obss.pokemon.model.PokemonTypeSaveRequestDTO;
import obss.pokemon.service.implementation.PokemonTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/pokemon-type")
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
}
