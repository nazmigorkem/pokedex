package obss.pokemon.service.implementation;

import obss.pokemon.entity.User;
import obss.pokemon.exceptions.ServiceException;
import obss.pokemon.model.user.UserSaveRequestDTO;
import obss.pokemon.model.user.UserSaveResponseDTO;
import obss.pokemon.repository.UserRepository;
import obss.pokemon.service.contract.UserServiceContract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserSaveResponseDTO addUser(UserSaveRequestDTO userSaveRequestDTO) {
        if (userRepository.existsByUsername(userSaveRequestDTO.getUsername())) {
            throw ServiceException.UserWithNameAlreadyExists(userSaveRequestDTO.getUsername());
        }
        return modelMapper.map(userRepository.save(modelMapper.map(userSaveRequestDTO, User.class)), UserSaveResponseDTO.class);
    }
}
