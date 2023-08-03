package obss.pokemon.service.implementation;

import obss.pokemon.entity.PokemonType;
import obss.pokemon.model.PokemonTypeResponseDTO;
import obss.pokemon.model.PokemonTypeSaveRequestDTO;
import obss.pokemon.model.PokemonTypeUpdateRequestDTO;
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
        return modelMapper.map(
                pokemonTypeRepository.save(
                        modelMapper.map(pokemonTypeSaveRequest, PokemonType.class)
                ),
                PokemonTypeResponseDTO.class
        );
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return pokemonTypeRepository.findByName(name).orElseThrow();
    }

    @Override
    public List<PokemonTypeResponseDTO> getPokemonTypeByNameStartsWith(String name) {
        return pokemonTypeRepository.findPokemonTypeByNameStartsWith(name).stream()
                .map(pokemonType -> modelMapper.map(pokemonType, PokemonTypeResponseDTO.class))
                .toList();
    }

    public PokemonTypeResponseDTO updatePokemonType(PokemonTypeUpdateRequestDTO pokemonTypeUpdateRequestDTO) {
        var pokemonType = pokemonTypeRepository.findByName(pokemonTypeUpdateRequestDTO.getSearchName()).orElseThrow();
        pokemonType.setName(pokemonTypeUpdateRequestDTO.getNewName());
        return modelMapper.map(
                pokemonTypeRepository.save(pokemonType),
                PokemonTypeResponseDTO.class
        );
    }

    @Override
    public void deletePokemonType(String name) {
        pokemonTypeRepository.delete(pokemonTypeRepository.findByName(name).orElseThrow());
    }
}
