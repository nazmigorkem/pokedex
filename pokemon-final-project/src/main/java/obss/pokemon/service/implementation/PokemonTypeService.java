package obss.pokemon.service.implementation;

import obss.pokemon.entity.PokemonType;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.pokemonType.PokemonTypeResponseDTO;
import obss.pokemon.model.pokemonType.PokemonTypeSaveRequestDTO;
import obss.pokemon.model.pokemonType.PokemonTypeUpdateRequestDTO;
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
    public PokemonTypeResponseDTO addPokemonType(PokemonTypeSaveRequestDTO pokemonTypeSaveRequest) {
        if (pokemonTypeRepository.existsByNameIgnoreCase(pokemonTypeSaveRequest.getName())) {
            throw new ServiceException(String.format("Pokemon type with name %s already exists.", pokemonTypeSaveRequest.getName()));
        }
        return modelMapper.map(
                pokemonTypeRepository.save(
                        modelMapper.map(pokemonTypeSaveRequest, PokemonType.class)
                ),
                PokemonTypeResponseDTO.class
        );
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        if (!pokemonTypeRepository.existsByNameIgnoreCase(name)) {
            throw new ServiceException(String.format("Pokemon type with name %s does not exist.", name));
        }
        return pokemonTypeRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<PokemonTypeResponseDTO> getPokemonTypeByNameStartsWith(String name) {
        return pokemonTypeRepository.findPokemonTypeByNameStartsWith(name).stream()
                .map(pokemonType -> modelMapper.map(pokemonType, PokemonTypeResponseDTO.class))
                .toList();
    }

    public PokemonTypeResponseDTO updatePokemonType(PokemonTypeUpdateRequestDTO pokemonTypeUpdateRequestDTO) {
        if (!pokemonTypeRepository.existsByNameIgnoreCase(pokemonTypeUpdateRequestDTO.getSearchName())) {
            throw new ServiceException(String.format("Pokemon type with name %s does not exist.", pokemonTypeUpdateRequestDTO.getSearchName()));
        }
        var pokemonType = pokemonTypeRepository.findByName(pokemonTypeUpdateRequestDTO.getSearchName()).orElseThrow();
        pokemonType.setName(pokemonTypeUpdateRequestDTO.getNewName());
        return modelMapper.map(
                pokemonTypeRepository.save(pokemonType),
                PokemonTypeResponseDTO.class
        );
    }

    @Override
    public void deletePokemonType(String name) {
        if (!pokemonTypeRepository.existsByNameIgnoreCase(name)) {
            throw new ServiceException(String.format("Pokemon type with name %s does not exist.", name));
        }
        pokemonTypeRepository.delete(pokemonTypeRepository.findByName(name).orElseThrow());
    }
}
