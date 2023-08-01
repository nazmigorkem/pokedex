package tech.obss.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserCacheSingleton implements UserService {

    Map<String, SaveUserRequestDTO> users;

    @PostConstruct
    public void init() {
        users = new TreeMap<>();
    }

    @Override
    public UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) {
        users.put(saveUserRequestDTO.getUsername(), saveUserRequestDTO);
        var savedUser = new UserResponseDTO();
        savedUser.setUsername(saveUserRequestDTO.getUsername());
        return savedUser;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return users.values().stream().map(x -> {
            var user = new UserResponseDTO();
            user.setUsername(x.getUsername());
            return user;
        }).toList();
    }
}
