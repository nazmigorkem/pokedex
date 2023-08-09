package obss.pokemon.model.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequest {
    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters.")
    private String username;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 3, max = 20, message = "Password should be between 3 and 20 characters.")
    private String password;

    private List<String> roles;
}
