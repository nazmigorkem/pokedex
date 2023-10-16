package obss.pokemon.service.implementation;

import jakarta.transaction.Transactional;
import obss.pokemon.config.DataLoader;
import obss.pokemon.entity.Role;
import obss.pokemon.entity.User;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.user.*;
import obss.pokemon.repository.PokemonRepository;
import obss.pokemon.repository.RoleRepository;
import obss.pokemon.repository.UserRepository;
import obss.pokemon.service.contract.UserServiceContract;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceContract, UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PokemonService pokemonService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PokemonRepository pokemonRepository;
    private final RoleService roleService;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, PokemonService pokemonService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PokemonRepository pokemonRepository, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.pokemonService = pokemonService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.pokemonRepository = pokemonRepository;
        this.roleService = roleService;
    }

    @Override
    public UserResponse addUser(UserSaveRequest userSaveRequest) {
        throwErrorIfUserExistsWithNameIgnoreCase(userSaveRequest.getUsername());
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var roles = authentication.getAuthorities();
        var user = new User();
        user.setUsername(userSaveRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        user.setRoles(Set.of(roleRepository.findByNameIgnoreCase(DataLoader.TRAINER_ROLE).orElseThrow()));
        if (userSaveRequest.getRoles() != null && roles.stream().map(GrantedAuthority::getAuthority).anyMatch(t -> t.equals(DataLoader.ADMIN_ROLE))) {
            userSaveRequest.getRoles().forEach(role -> {
                roleService.throwErrorIfRoleDoesNotExistWithNameIgnoreCase(role.getName());
                var roleEntity = roleRepository.findByNameIgnoreCase(role.getName()).orElseThrow();
                var userRoles = new HashSet<>(user.getRoles());
                userRoles.add(roleEntity);
                user.setRoles(userRoles);
            });
        }
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
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName()).orElseThrow();
        user.getCatchList().add(pokemon);
        return getUserResponse(user);
    }

    @Override
    public UserResponse addPokemonToWishListOfUser(UserPokemonAddRequest userPokemonAddRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getUsername());
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        var user = userRepository.findByUsernameIgnoreCase(userPokemonAddRequest.getUsername()).orElseThrow();
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName()).orElseThrow();
        user.getWishlist().add(pokemon);
        return getUserResponse(user);
    }

    @Override
    public Page<PokemonResponse> getCatchListOfUser(String username, int pageNumber, int pageSize) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        return pokemonService.getCatchListOfUser(username, pageNumber, pageSize);
    }

    @Override
    public Page<PokemonResponse> getWishListOfUser(String username, int pageNumber, int pageSize) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        return pokemonService.getWishListOfUser(username, pageNumber, pageSize);
    }

    public boolean isPokemonInCatchList(String username, String pokemonName) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonName);
        return userRepository.isPokemonInCatchList(username, pokemonName);
    }

    public boolean isPokemonInWishList(String username, String pokemonName) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonName);
        return userRepository.isPokemonInWishList(username, pokemonName);
    }

    @Override
    public UserResponse deletePokemonFromCatchListOfUser(UserPokemonAddRequest userPokemonAddRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getUsername());
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        var user = userRepository.findByUsernameIgnoreCase(userPokemonAddRequest.getUsername()).orElseThrow();
        throwErrorIfPokemonDoesNotExistInCatchList(user, userPokemonAddRequest.getPokemonName());
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName()).orElseThrow();
        user.getCatchList().remove(pokemon);
        return getUserResponse(user);
    }

    @Override
    public UserResponse deletePokemonFromWishListOfUser(UserPokemonAddRequest userPokemonAddRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getUsername());
        pokemonService.throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(userPokemonAddRequest.getPokemonName());
        var user = userRepository.findByUsernameIgnoreCase(userPokemonAddRequest.getUsername()).orElseThrow();
        throwErrorIfPokemonDoesNotExistInWishList(user, userPokemonAddRequest.getPokemonName());
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(userPokemonAddRequest.getPokemonName()).orElseThrow();
        user.getWishlist().remove(pokemon);
        return getUserResponse(user);
    }

    @Override
    public UserHeartbeatResponse getUserHeartbeatByUsernameIgnoreCase(String username) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        var user = userRepository.findByUsernameIgnoreCase(username).orElseThrow();
        var userHeartbeatResponse = new UserHeartbeatResponse();
        userHeartbeatResponse.setUsername(user.getUsername());
        userHeartbeatResponse.setRoles(user.getRoles().stream().map(Role::getName).toList());
        return userHeartbeatResponse;
    }

    @Override
    public Page<UserSearchResponse> getUserByUsernameStartsWithIgnoreCase(String username, int pageNumber, int pageSize) {
        return userRepository.findByUsernameStartsWithIgnoreCase(username, PageRequest.of(pageNumber, pageSize)).map(x -> {
            var userSearchResponse = new UserSearchResponse();
            userSearchResponse.setUsername(x.getUsername());
            userSearchResponse.setRoles(x.getRoles().stream().map(Role::getName).toList());
            return userSearchResponse;
        });
    }

    @Override
    public void deleteUserByUsernameIgnoreCase(String username) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(username);
        var user = userRepository.findByUsernameIgnoreCase(username).orElseThrow();
        user.getRoles().forEach(x -> x.getUsers().remove(user));
        user.getCatchList().forEach(x -> x.getUsersCatchList().remove(user));
        user.getWishlist().forEach(x -> x.getUsersWishList().remove(user));
        userRepository.delete(user);
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {
        throwErrorIfUserDoesNotExistWithNameIgnoreCase(userUpdateRequest.getSearchUsername());
        var user = userRepository.findByUsernameIgnoreCase(userUpdateRequest.getSearchUsername()).orElseThrow();
        if (userUpdateRequest.getUsername() != null) {
            throwErrorIfUserExistsWithNameIgnoreCase(userUpdateRequest.getUsername());
            user.setUsername(userUpdateRequest.getUsername());
        }
        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (userUpdateRequest.getRoles() != null) {
            user.getRoles().forEach(x -> x.getUsers().remove(user));
            user.setRoles(userUpdateRequest.getRoles().stream().map(x -> roleRepository.findByNameIgnoreCase(x.getName()).orElseThrow()).collect(Collectors.toSet()));
            user.getRoles().forEach(x -> x.getUsers().add(user));
        }
        return getUserResponse(user);
    }

    private UserResponse getUserResponse(User user) {
        userRepository.save(user);
        var userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setCatchList(user.getCatchList().stream().map(x -> modelMapper.map(x, PokemonResponse.class)).toList());
        userResponse.setWishList(user.getWishlist().stream().map(x -> modelMapper.map(x, PokemonResponse.class)).toList());
        return userResponse;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new RuntimeException("User not found!"));
        Hibernate.initialize(user.getRoles());
        return new MyUserDetails(user);
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

    private void throwErrorIfPokemonDoesNotExistInCatchList(User user, String pokemonName) {
        if (user.getCatchList().stream().noneMatch(x -> x.getName().equalsIgnoreCase(pokemonName))) {
            throw ServiceException.PokemonNotInCatchList(pokemonName);
        }
    }

    private void throwErrorIfPokemonDoesNotExistInWishList(User user, String pokemonName) {
        if (user.getWishlist().stream().noneMatch(x -> x.getName().equalsIgnoreCase(pokemonName))) {
            throw ServiceException.PokemonNotInWishList(pokemonName);
        }
    }
}
