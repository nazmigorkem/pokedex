package obss.pokemon.service.implementation;

import obss.pokemon.entity.User;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.user.UserPokemonAddRequest;
import obss.pokemon.model.user.UserResponse;
import obss.pokemon.model.user.UserSaveRequest;
import obss.pokemon.repository.UserRepository;
import obss.pokemon.service.contract.UserServiceContract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PokemonService pokemonService;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, PokemonService pokemonService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.pokemonService = pokemonService;
    }

    @Override
    public UserResponse addUser(UserSaveRequest userSaveRequest) {
        throwErrorIfUserExistsWithNameIgnoreCase(userSaveRequest.getUsername());
        var user = modelMapper.map(userSaveRequest, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByUsernameIgnoreCase(String username) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        var user = userRepository.findByUsernameIgnoreCase(username).orElseThrow();
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse addPokemonToCatchListOfUser(UserPokemonAddRequest userPokemonAddRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getUsername());
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        var user = userRepository.findByUsernameIgnoreCase(userPokemonAddRequest.getUsername()).orElseThrow();
        var pokemon = pokemonService.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        user.getCatchList().add(pokemon);
        return getUserResponse(user);
    }

    @Override
    public UserResponse addPokemonToWishListOfUser(UserPokemonAddRequest userPokemonAddRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getUsername());
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        var user = userRepository.findByUsernameIgnoreCase(userPokemonAddRequest.getUsername()).orElseThrow();
        var pokemon = pokemonService.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        user.getWishlist().add(pokemon);
        return getUserResponse(user);
    }

    private UserResponse getUserResponse(User user) {
        userRepository.save(user);
        var userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setCatchList(user.getCatchList().stream().map(x -> modelMapper.map(x, PokemonResponse.class)).toList());
        userResponse.setWishList(user.getWishlist().stream().map(x -> modelMapper.map(x, PokemonResponse.class)).toList());
        return userResponse;
    }

    //*****************//
    //* GUARD CLAUSES *//
    //*************** *//

    private void throwErrorIfUserExistsWithNameIgnoreCase(String username) {
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw ServiceException.UserWithNameAlreadyExists(username);
        }
    }

    private void throwErrorIfUserDoesNotExistWithNameIgnoreCase(String username) {
        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw ServiceException.UserWithUsernameNotFound(username);
        }
    }
}
