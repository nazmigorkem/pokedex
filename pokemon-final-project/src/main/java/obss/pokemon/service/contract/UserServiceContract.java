package obss.pokemon.service.contract;

import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.user.*;
import org.springframework.data.domain.Page;

public interface UserServiceContract {
    UserResponse addUser(UserSaveRequest userSaveRequest);

    UserResponse getUserByUsernameIgnoreCase(String username);

    UserResponse addPokemonToCatchListOfUser(UserPokemonAddRequest userPokemonAddRequest);

    UserResponse addPokemonToWishListOfUser(UserPokemonAddRequest userPokemonAddRequest);

    UserResponse deletePokemonFromCatchListOfUser(UserPokemonAddRequest userPokemonAddRequest);

    UserResponse deletePokemonFromWishListOfUser(UserPokemonAddRequest userPokemonAddRequest);

    void deleteUserByUsernameIgnoreCase(String username);

    Page<UserSearchResponse> getUserByUsernameStartsWithIgnoreCase(String username, int pageNumber, int pageSize);

    Page<PokemonResponse> getCatchListOfUser(String username, int pageNumber, int pageSize);

    Page<PokemonResponse> getWishListOfUser(String username, int pageNumber, int pageSize);

    UserHeartbeatResponse getUserHeartbeatByUsernameIgnoreCase(String username);

    boolean isPokemonInCatchList(String username, String pokemonName);

    boolean isPokemonInWishList(String username, String pokemonName);

}
