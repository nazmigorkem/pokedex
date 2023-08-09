package obss.pokemon.service.implementation;

import obss.pokemon.entity.Pokemon;
import obss.pokemon.entity.PokemonType;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.pokemon.PokemonSaveRequest;
import obss.pokemon.model.pokemon.PokemonUpdateRequest;
import obss.pokemon.repository.PokemonRepository;
import obss.pokemon.service.contract.PokemonServiceContract;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class PokemonService implements PokemonServiceContract {

    private final ModelMapper modelMapper;
    private final PokemonRepository pokemonRepository;
    private final PokemonTypeService pokemonTypeService;

    public PokemonService(ModelMapper modelMapper, PokemonRepository pokemonRepository, PokemonTypeService pokemonTypeService) {
        this.modelMapper = modelMapper;
        this.pokemonRepository = pokemonRepository;
        this.pokemonTypeService = pokemonTypeService;
    }

    @Override
    public PokemonResponse addPokemon(PokemonSaveRequest pokemonSaveRequest) {
        throwErrorIfPokemonExistsWithNameIgnoreCase(pokemonSaveRequest.getName());
        var pokemonTypes = pokemonSaveRequest.getTypes().stream().map(pokemonTypeService::getPokemonTypeByNameIgnoreCase).collect(Collectors.toSet());
        var newPokemon = modelMapper.map(pokemonSaveRequest, Pokemon.class);
        newPokemon.setTypes(pokemonTypes);
        return modelMapper.map(
                pokemonRepository.save(newPokemon),
                PokemonResponse.class
        );
    }

    @Override
    public Page<PokemonResponse> getPokemonByNameStartsWithIgnoreCase(String pokemonName, int pageNumber, int pageSize) {
        return pokemonRepository.getPokemonByNameStartsWithIgnoreCase(pokemonName, PageRequest.of(pageNumber, pageSize)).map(pokemon -> modelMapper.map(pokemon, PokemonResponse.class));
    }

    @Override
    public PokemonResponse getPokemonByNameIgnoreCase(String pokemonName) {
        throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonName);
        return modelMapper.map(pokemonRepository.getPokemonByNameIgnoreCase(pokemonName).orElseThrow(), PokemonResponse.class);
    }

    @Override
    public void deletePokemon(String pokemonName) {
        throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonName);
        pokemonRepository.delete(pokemonRepository.getPokemonByNameIgnoreCase(pokemonName).orElseThrow());
    }

    @Override
    public PokemonResponse updatePokemon(PokemonUpdateRequest pokemonUpdateRequest) {
        throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonUpdateRequest.getSearchName());
        throwErrorIfPokemonExistsWithNameIgnoreCase(pokemonUpdateRequest.getName());
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(pokemonUpdateRequest.getSearchName()).orElseThrow();
        if (pokemonUpdateRequest.getTypes() != null) {
            var pokemonTypes = new HashSet<PokemonType>();
            pokemonUpdateRequest.getTypes().stream().map(pokemonTypeService::getPokemonTypeByNameIgnoreCase).forEach(pokemonTypes::add);
            pokemon.setTypes(pokemonTypes);
        }
        modelMapper.map(pokemonUpdateRequest, pokemon);
        return modelMapper.map(pokemonRepository.save(pokemon), PokemonResponse.class);
    }

    @Override
    public Page<PokemonResponse> getCatchListOfUser(String username, int pageNumber, int pageSize) {
        return pokemonRepository.getAllByUsersCatchList_UsernameIgnoreCase(username, PageRequest.of(pageNumber, pageSize)).map(pokemon -> modelMapper.map(pokemon, PokemonResponse.class));
    }

    @Override
    public Page<PokemonResponse> getWishListOfUser(String username, int pageNumber, int pageSize) {
        return pokemonRepository.getAllByUsersWishList_UsernameIgnoreCase(username, PageRequest.of(pageNumber, pageSize)).map(pokemon -> modelMapper.map(pokemon, PokemonResponse.class));
    }

    //*****************//
    //* GUARD CLAUSES *//
    //*************** *//

    public void throwErrorIfPokemonExistsWithNameIgnoreCase(String pokemonName) {
        if (pokemonRepository.existsByNameIgnoreCase(pokemonName)) {
            throw ServiceException.PokemonWithNameAlreadyExists(pokemonName);
        }
    }

    public void throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(String pokemonName) {
        if (!pokemonRepository.existsByNameIgnoreCase(pokemonName)) {
            throw ServiceException.PokemonWithNameNotFound(pokemonName);
        }
    }

}
