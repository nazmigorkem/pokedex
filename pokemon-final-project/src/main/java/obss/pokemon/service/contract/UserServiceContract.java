package obss.pokemon.service.contract;

import obss.pokemon.model.user.UserPokemonAddRequest;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.model.user.UserSaveRequest;

public interface UserServiceContract {
    UserResponse addUser(UserSaveRequest userSaveRequest);

    UserResponse getUserByUsername(String username);

    UserResponse addPokemonToCatchListOfUser(UserPokemonAddRequest userPokemonAddRequest);

    UserResponse addPokemonToWishListOfUser(UserPokemonAddRequest userPokemonAddRequest);
}
