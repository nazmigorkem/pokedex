package obss.pokemon.service.implementation;

import obss.pokemon.entity.Pokemon;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemon.PokemonResponseDTO;
import obss.pokemon.model.pokemon.PokemonSaveRequestDTO;
import obss.pokemon.model.pokemon.PokemonUpdateRequestDTO;
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
    public PokemonResponseDTO addPokemon(PokemonSaveRequestDTO pokemonSaveRequestDTO) {
        throwErrorIfPokemonExistsWithName(pokemonSaveRequestDTO.getName());
        var pokemonTypes = pokemonSaveRequestDTO.getTypes().stream().map(pokemonTypeService::getPokemonTypeByName).collect(Collectors.toSet());
        var newPokemon = modelMapper.map(pokemonSaveRequestDTO, Pokemon.class);
        newPokemon.setTypes(pokemonTypes);
        return modelMapper.map(
                pokemonRepository.save(newPokemon),
                PokemonResponseDTO.class
        );
    }


    @Override
    public List<PokemonResponseDTO> getPokemonByNameStartsWith(String name) {
        return pokemonRepository.getPokemonByNameStartsWith(name).stream()
                .map(pokemon -> modelMapper.map(pokemon, PokemonResponseDTO.class)).toList();
    }

    @Override
    public void deletePokemon(String name) {
        throwErrorIfPokemonIsNotExistsWithName(name);
        pokemonRepository.delete(pokemonRepository.getPokemonByNameIgnoreCase(name).orElseThrow());
    }

    @Override
    public PokemonResponseDTO updatePokemon(PokemonUpdateRequestDTO pokemonUpdateRequestDTO) {
        throwErrorIfPokemonIsNotExistsWithName(pokemonUpdateRequestDTO.getSearchName());
        throwErrorIfPokemonExistsWithName(pokemonUpdateRequestDTO.getName());
        var pokemon = pokemonRepository.getPokemonByNameIgnoreCase(pokemonUpdateRequestDTO.getSearchName()).orElseThrow();
        modelMapper.map(pokemonUpdateRequestDTO, pokemon);
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonResponseDTO.class);
    }


    //*****************//
    //* GUARD CLAUSES *//
    //*************** *//

    private void throwErrorIfPokemonExistsWithName(String pokemonName) {
        if (pokemonRepository.existsByNameIgnoreCase(pokemonName)) {
            throw ServiceException.PokemonWithNameAlreadyExists(pokemonName);
        }
    }

    private void throwErrorIfPokemonIsNotExistsWithName(String pokemonName) {
        if (!pokemonRepository.existsByNameIgnoreCase(pokemonName)) {
            throw ServiceException.PokemonNotFound(pokemonName);
        }
    }

}
