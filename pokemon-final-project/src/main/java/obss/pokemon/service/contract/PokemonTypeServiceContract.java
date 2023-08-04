package obss.pokemon.service.contract;

import obss.pokemon.entity.PokemonType;
import obss.pokemon.model.pokemonType.PokemonTypeResponseDTO;
import obss.pokemon.model.pokemonType.PokemonTypeSaveRequestDTO;

import java.util.List;

public interface PokemonTypeServiceContract {
    PokemonTypeResponseDTO addPokemonType(PokemonTypeSaveRequestDTO pokemonTypeSaveRequest);

    List<PokemonTypeResponseDTO> getPokemonTypeByNameStartsWith(String name);

    PokemonType getPokemonTypeByName(String name);


    void deletePokemonType(String name);
}
