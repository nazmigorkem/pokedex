package obss.pokemon.service.contract;

import obss.pokemon.model.pokemon.PokemonResponseDTO;
import obss.pokemon.model.pokemon.PokemonSaveRequestDTO;
import obss.pokemon.model.pokemon.PokemonUpdateRequestDTO;

import java.util.List;

public interface PokemonServiceContract {
    PokemonResponseDTO addPokemon(PokemonSaveRequestDTO pokemonSaveRequest);

    List<PokemonResponseDTO> getPokemonByNameStartsWith(String name);

    void deletePokemon(String name);

    PokemonResponseDTO updatePokemon(PokemonUpdateRequestDTO pokemonUpdateRequestDTO);

}
