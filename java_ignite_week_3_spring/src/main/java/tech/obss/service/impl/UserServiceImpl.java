package tech.obss.service.impl;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.obss.entity.Role;
import tech.obss.entity.User;
import tech.obss.model.MyUserDetails;
import tech.obss.model.SaveUserRequestDTO;
import tech.obss.model.UpdateUserRequestDTO;
import tech.obss.model.UserResponseDTO;
import tech.obss.repository.RoleRepository;
import tech.obss.repository.UserDAO;
import tech.obss.repository.UserRepository;
import tech.obss.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Primary
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDAO userDAO;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserDAO userDAO, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userDAO = userDAO;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO saveUser(SaveUserRequestDTO saveUserRequestDTO) {
        var user = new User();
        user.setUsername(saveUserRequestDTO.getUsername());
        user.setPassword(saveUserRequestDTO.getPassword());
        var roleUser = roleRepository.findByName("ROLE_USER");
        roleUser.ifPresent(x -> user.setRoles(Set.of(x)));
        var savedUser = userRepository.save(user);
        return mapUserToUserResponseDTO(savedUser);
    }

    private UserResponseDTO mapUserToUserResponseDTO(User user) {
        var userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setId(user.getId());
        userResponseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
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
    public List<UserResponseDTO> getUsersWithRoleNames(List<String> roleNames) {
        return userRepository.findByRoles_NameIn(roleNames).stream().map(this::mapUserToUserResponseDTO).toList();
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

    @Override
    public List<UserResponseDTO> getUserByUsernameAlike(String username) {
        return userRepository
                .findByUsernameStartsWithAndActiveIsTrueOrderByCreatedDateDesc(username).stream()
                .map(this::mapUserToUserResponseDTO).toList();
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return mapUserToUserResponseDTO(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public Page<UserResponseDTO> getUsersPage(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(this::mapUserToUserResponseDTO);
    }

    @Override
    public UserResponseDTO getUserByIdHQL(long id) {
        return mapUserToUserResponseDTO(userRepository.findByIdWithHQL(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public UserResponseDTO getUserByIdNativeQuery(long id) {
        return mapUserToUserResponseDTO(userRepository.findByIdWithNativeQuery(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public List<UserResponseDTO> getUsersPageWithDAO(int pageNumber, int pageSize) {
        return userDAO.getUsers(pageNumber, pageSize).stream().map(this::mapUserToUserResponseDTO).toList();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Hibernate.initialize(user.getRoles());
        return new MyUserDetails(user);
    }
}
