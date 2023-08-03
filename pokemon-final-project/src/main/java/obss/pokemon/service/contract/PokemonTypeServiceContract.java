package obss.pokemon.service.contract;

import obss.pokemon.model.PokemonTypeResponseDTO;
import obss.pokemon.model.PokemonTypeSaveRequestDTO;

import java.util.List;

public interface PokemonTypeServiceContract {
    PokemonTypeResponseDTO addPokemonType(PokemonTypeSaveRequestDTO pokemonTypeSaveRequest);

    List<PokemonTypeResponseDTO> getPokemonTypeByNameStartsWith(String name);

    PokemonTypeResponseDTO getPokemonTypeByName(String name);


    void deletePokemonType(String name);
}
