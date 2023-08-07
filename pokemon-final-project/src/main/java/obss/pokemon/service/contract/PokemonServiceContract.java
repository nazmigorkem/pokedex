package obss.pokemon.service.contract;

import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.pokemon.PokemonSaveRequest;
import obss.pokemon.model.pokemon.PokemonUpdateRequest;
import org.springframework.data.domain.Page;

public interface PokemonServiceContract {
    PokemonResponse addPokemon(PokemonSaveRequest pokemonSaveRequest);

    Page<PokemonResponse> getPokemonByNameStartsWithIgnoreCase(String name, int pageNumber, int pageSize);

    void deletePokemon(String name);

    PokemonResponse updatePokemon(PokemonUpdateRequest pokemonUpdateRequest);

    PokemonResponse getPokemonByNameIgnoreCase(String name);

    Page<PokemonResponse> getCatchListOfUser(String username, int pageNumber, int pageSize);

    Page<PokemonResponse> getWishListOfUser(String username, int pageNumber, int pageSize);

}
