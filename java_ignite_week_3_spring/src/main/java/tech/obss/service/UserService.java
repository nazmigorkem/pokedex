package tech.obss.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import tech.obss.entity.User;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;
import tech.obss.model.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO);

    List<UserResponseDTO> getUsers();

    default User getUserById(long id) {
        throw new NotImplementedException();
    }

    default UserResponseDTO updateUser(long id, UpdateUserRequestDTO user) {
        throw new NotImplementedException();
    }

    default UserResponseDTO deleteUser(long id) {
        throw new NotImplementedException();
    }

    default UserResponseDTO getUser(long id) {
        throw new NotImplementedException();
    }

    default List<UserResponseDTO> getUserByUsernameAlike(String username) {
        throw new NotImplementedException();
    }

    default UserResponseDTO getUserByUsername(String username) {
        throw new NotImplementedException();
    }

    default Page<UserResponseDTO> getUsersPage(int page, int size) {
        throw new NotImplementedException();
    }
}
