package obss.pokemon.service.contract;

import obss.pokemon.model.PokemonResponseDTO;
import obss.pokemon.model.PokemonSaveRequestDTO;

import java.util.List;

public interface PokemonServiceContract {
    PokemonResponseDTO addPokemon(PokemonSaveRequestDTO pokemonSaveRequest);

    List<PokemonResponseDTO> getPokemonByNameStartsWith(String name);

    void deletePokemon();

    void updatePokemon();

}
