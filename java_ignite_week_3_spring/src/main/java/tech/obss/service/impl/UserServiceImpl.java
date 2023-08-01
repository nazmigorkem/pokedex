package tech.obss.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.obss.entity.User;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.repository.UserRepository;
import tech.obss.service.UserService;

import java.util.List;


@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) {
        var user = new User();
        user.setUsername(saveUserRequestDTO.getUsername());
        user.setPassword(saveUserRequestDTO.getPassword());
        var savedUser = userRepository.save(user);
        var responseDTO = new UserResponseDTO();
        responseDTO.setUsername(savedUser.getUsername());
        responseDTO.setId(savedUser.getId());
        return responseDTO;
    }

    private UserResponseDTO mapUserToUserResponseDTO(User user) {
        var userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setId(user.getId());
        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(x -> {
            var user = new UserResponseDTO();
            user.setUsername(x.getUsername());
            user.setId(x.getId());
            return user;
        }).toList();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDTO updateUser(long id, UpdateUserRequestDTO updateUserRequestDTO) {
        var user = getUserById(id);
        user.setPassword(updateUserRequestDTO.getPassword());
        userRepository.save(user);
        return mapUserToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO deleteUser(long id) {
        var user = getUserById(id);
        user.setActive(!user.isActive());
        userRepository.save(user);
        return mapUserToUserResponseDTO(user);
    }

    @Override
    public UserResponseDTO getUser(long id) {
        return mapUserToUserResponseDTO(getUserById(id));
    }
}
