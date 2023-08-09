package obss.pokemon.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import obss.pokemon.model.role.RoleResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank(message = "Search username cannot be blank.")
    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters.")
    private String searchUsername;

    private String username;

    private String password;

    @Size(min = 1, message = "User should have at least 1 role.")
    private List<RoleResponse> roles;
}
