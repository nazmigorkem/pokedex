package obss.pokemon.service.contract;

import obss.pokemon.model.role.RoleResponse;

import java.util.List;

public interface RoleContract {
    List<RoleResponse> getAllRoles();
}
