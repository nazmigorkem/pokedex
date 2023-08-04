package obss.pokemon.controller;

import jakarta.validation.Valid;
import obss.pokemon.model.user.UserSaveRequestDTO;
import obss.pokemon.model.user.UserSaveResponseDTO;
import obss.pokemon.service.implementation.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserSaveResponseDTO> addUser(@Valid @RequestBody UserSaveRequestDTO userSaveRequestDTO) {
        return ResponseEntity.ok(userService.addUser(userSaveRequestDTO));
    }
}
