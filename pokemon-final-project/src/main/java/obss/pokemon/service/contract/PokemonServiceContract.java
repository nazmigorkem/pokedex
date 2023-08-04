package obss.pokemon.service.contract;

import obss.pokemon.model.pokemon.PokemonResponseDTO;
import obss.pokemon.model.pokemon.PokemonSaveRequestDTO;

import java.util.List;

public interface PokemonServiceContract {
    PokemonResponseDTO addPokemon(PokemonSaveRequestDTO pokemonSaveRequest);

    List<PokemonResponseDTO> getPokemonByNameStartsWith(String name);

    void deletePokemon(String name);

    void updatePokemon();

}
