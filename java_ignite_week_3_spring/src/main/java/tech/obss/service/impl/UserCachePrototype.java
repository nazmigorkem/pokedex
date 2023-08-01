package tech.obss.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Scope("prototype")
public class UserCachePrototype implements UserService {

    private Map<String, SaveUserRequestDTO> users;
    private final Logger LOGGER = LoggerFactory.getLogger(UserCachePrototype.class);

    @PostConstruct
    public void init() {
        LOGGER.info("UserCachePrototype is initialized");
        users = new TreeMap<>();
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("UserCachePrototype is destroyed");
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
