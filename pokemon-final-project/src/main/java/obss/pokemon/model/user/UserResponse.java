package obss.pokemon.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import obss.pokemon.model.pokemon.PokemonResponse;
import obss.pokemon.model.role.RoleResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String username;
    private List<RoleResponse> roles;
    private List<PokemonResponse> catchList;
    private List<PokemonResponse> wishList;
}
