package obss.pokemon.service.contract;

import obss.pokemon.entity.Pokemon;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.pokemon.PokemonSaveRequest;
import obss.pokemon.model.pokemon.PokemonUpdateRequest;

import java.util.List;

public interface PokemonServiceContract {
    PokemonResponse addPokemon(PokemonSaveRequest pokemonSaveRequest);

    List<PokemonResponse> getPokemonByNameStartsWithIgnoreCase(String name);

    void deletePokemon(String name);

    PokemonResponse updatePokemon(PokemonUpdateRequest pokemonUpdateRequest);

    Pokemon getPokemonByNameIgnoreCase(String name);

}
