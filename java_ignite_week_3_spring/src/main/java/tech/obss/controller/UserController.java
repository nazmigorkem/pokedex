package tech.obss.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUsers(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/hql/{id}")
    public ResponseEntity<UserResponseDTO> getUsersWithHQL(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserByIdHQL(id));
    }

    @GetMapping("/native-query/{id}")
    public ResponseEntity<UserResponseDTO> getUsersWithNativeQuery(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserByIdNativeQuery(id));
    }

    @GetMapping("/username-alike")
    public ResponseEntity<List<UserResponseDTO>> getUserByUsernameAlike(@RequestParam(defaultValue = "") String username) {
        return ResponseEntity.ok(userService.getUserByUsernameAlike(username));
    }

    @GetMapping("/username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam(defaultValue = "") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserResponseDTO>> getUsersPage(@RequestParam(defaultValue = "0") int number, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(userService.getUsersPage(number, size));
    }

    @GetMapping("/page-with-dao")
    public ResponseEntity<List<UserResponseDTO>> getUsersPageWithDAO(@RequestParam(defaultValue = "0") int number, @RequestParam(defaultValue = "0") int size) {
        return ResponseEntity.ok(userService.getUsersPageWithDAO(number, size));
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody SaveUserRequestDTO saveUserRequestDTO) {
        return ResponseEntity.ok(userService.saveUser(saveUserRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") long id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequestDTO));
    }
}
