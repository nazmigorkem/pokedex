package obss.pokemon.service.contract;

import obss.pokemon.entity.PokemonType;
import obss.pokemon.model.pokemonType.PokemonTypeResponse;
import obss.pokemon.model.pokemonType.PokemonTypeSaveRequest;

import java.util.List;

public interface PokemonTypeServiceContract {
    PokemonTypeResponse addPokemonType(PokemonTypeSaveRequest pokemonTypeSaveRequest);

    List<PokemonTypeResponse> getPokemonTypeByNameStartsWith(String name);

    PokemonType getPokemonTypeByName(String name);

    void deletePokemonType(String name);
}
