package obss.pokemon.service.implementation;

import obss.pokemon.entity.PokemonType;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemonType.PokemonTypeResponse;
import obss.pokemon.model.pokemonType.PokemonTypeSaveRequest;
import obss.pokemon.model.pokemonType.PokemonTypeUpdateRequest;
import obss.pokemon.repository.PokemonTypeRepository;
import obss.pokemon.service.contract.PokemonTypeServiceContract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTypeService implements PokemonTypeServiceContract {


    private final PokemonTypeRepository pokemonTypeRepository;
    private final ModelMapper modelMapper;

    public PokemonTypeService(PokemonTypeRepository pokemonTypeRepository, ModelMapper modelMapper) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PokemonTypeResponse addPokemonType(PokemonTypeSaveRequest pokemonTypeSaveRequest) {
        throwErrorIfPokemonTypeExistsWithNameIgnoreCase(pokemonTypeSaveRequest);
        return modelMapper.map(
                pokemonTypeRepository.save(
                        modelMapper.map(pokemonTypeSaveRequest, PokemonType.class)
                ),
                PokemonTypeResponse.class
        );
    }

    @Override
    public PokemonType getPokemonTypeByNameIgnoreCase(String name) {
        throwErrorIfPokemonTypeDoesNotExistWithNameIgnoreCase(name);
        return pokemonTypeRepository.findByNameIgnoreCase(name).orElseThrow();
    }

    @Override
    public List<PokemonTypeResponse> getPokemonTypeByNameStartsWith(String name) {
        return pokemonTypeRepository.findPokemonTypeByNameStartsWithIgnoreCase(name).stream()
                .map(pokemonType -> modelMapper.map(pokemonType, PokemonTypeResponse.class))
                .toList();
    }

    public PokemonTypeResponse updatePokemonType(PokemonTypeUpdateRequest pokemonTypeUpdateRequest) {
        throwErrorIfPokemonTypeDoesNotExistWithNameIgnoreCase(pokemonTypeUpdateRequest.getSearchName());
        var pokemonType = pokemonTypeRepository.findByNameIgnoreCase(pokemonTypeUpdateRequest.getSearchName()).orElseThrow();
        pokemonType.setName(pokemonTypeUpdateRequest.getNewName());
        pokemonType.setColor(pokemonTypeUpdateRequest.getNewColor());
        return modelMapper.map(
                pokemonTypeRepository.save(pokemonType),
                PokemonTypeResponse.class
        );
    }

    @Override
    public void deletePokemonType(String name) {
        throwErrorIfPokemonTypeDoesNotExistWithNameIgnoreCase(name);
        pokemonTypeRepository.delete(pokemonTypeRepository.findByNameIgnoreCase(name).orElseThrow());
    }

    //*****************//
    //* GUARD CLAUSES *//
    //*************** *//

    public void throwErrorIfPokemonTypeDoesNotExistWithNameIgnoreCase(String name) {
        if (!pokemonTypeRepository.existsByNameIgnoreCase(name)) {
            throw ServiceException.PokemonTypeWithNameNotFound(name);
        }
    }

    public void throwErrorIfPokemonTypeExistsWithNameIgnoreCase(PokemonTypeSaveRequest pokemonTypeSaveRequest) {
        if (pokemonTypeRepository.existsByNameIgnoreCase(pokemonTypeSaveRequest.getName())) {
            throw ServiceException.PokemonTypeWithNameAlreadyExists(pokemonTypeSaveRequest.getName());
        }
    }
}
