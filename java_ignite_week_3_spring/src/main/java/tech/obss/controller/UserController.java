package tech.obss.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.service.UserService;
import tech.obss.service.impl.UserCachePrototype;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ApplicationContext applicationContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(ApplicationContext applicationContext, UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUsers(@PathVariable("id") long id) {
        return ResponseEntity.ok("Get user: " + id);
    }

    @PostMapping("")
    public ResponseEntity<Map<?, ?>> saveUser(@Valid @RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        UserService userCachePrototype = applicationContext.getBean(UserCachePrototype.class);
        UserService userCacheSingleton = userService;

        userCacheSingleton.saveUser(saveUserRequestDTO);
        userCachePrototype.saveUser(saveUserRequestDTO);
        return ResponseEntity.ok(Map.of("singleton", userCacheSingleton.getUsers(), "prototype", userCachePrototype.getUsers()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok("Delete user: " + id);
    }

    @PutMapping("/{id}")
    public UpdateUserRequestDTO updateUser(@PathVariable("id") long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return updateUserRequestDTO;
    }
}
