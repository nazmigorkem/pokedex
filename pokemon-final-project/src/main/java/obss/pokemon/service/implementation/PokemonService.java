package obss.pokemon.service.implementation;

import obss.pokemon.entity.Pokemon;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.pokemon.PokemonSaveRequest;
import obss.pokemon.model.pokemon.PokemonUpdateRequest;
import obss.pokemon.repository.PokemonRepository;
import obss.pokemon.service.contract.PokemonServiceContract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        var pokemonTypes = pokemonSaveRequest.getTypes().stream().map(pokemonTypeService::getPokemonTypeByName).collect(Collectors.toSet());
        var newPokemon = modelMapper.map(pokemonSaveRequest, Pokemon.class);
        newPokemon.setTypes(pokemonTypes);
        return modelMapper.map(
                pokemonRepository.save(newPokemon),
                PokemonResponse.class
        );
    }
    
    @Override
    public List<PokemonResponse> getPokemonByNameStartsWithIgnoreCase(String pokemonName) {
        return pokemonRepository.getPokemonByNameStartsWithIgnoreCase(pokemonName).stream()
                .map(pokemon -> modelMapper.map(pokemon, PokemonResponse.class)).toList();
    }

    @Override
    public Pokemon getPokemonByNameIgnoreCase(String pokemonName) {
        throwErrorIfPokemonDoesNotExistWithNameIgnoreCase(pokemonName);
        return pokemonRepository.getPokemonByNameIgnoreCase(pokemonName).orElseThrow();
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
            pokemonUpdateRequest.getTypes().stream().map(pokemonTypeService::getPokemonTypeByName).forEach(pokemon::addType);
        }
        modelMapper.map(pokemonUpdateRequest, pokemon);
        return modelMapper.map(pokemonRepository.save(pokemon), PokemonResponse.class);
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
