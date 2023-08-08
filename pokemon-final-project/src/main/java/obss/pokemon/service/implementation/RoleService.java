package obss.pokemon.service.implementation;

import obss.pokemon.model.role.RoleResponse;
import obss.pokemon.repository.RoleRepository;
import obss.pokemon.service.contract.RoleContract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleContract {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(role -> modelMapper.map(role, RoleResponse.class)).toList();
    }
}
