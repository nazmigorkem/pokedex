package obss.pokemon.controller;

import obss.pokemon.model.role.RoleResponse;
import obss.pokemon.service.implementation.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/search")
    public List<RoleResponse> getAllRoles() {
        return roleService.getAllRoles();
    }
}
