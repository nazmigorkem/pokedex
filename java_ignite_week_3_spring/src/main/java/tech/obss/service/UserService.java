package tech.obss.service;

import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO);

    List<UserResponseDTO> getUsers();
}
