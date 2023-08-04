package obss.pokemon.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPokemonAddRequest {
    @NotBlank(message = "Pokemon name cannot be blank.")
    private String pokemonName;

    @NotBlank(message = "Username cannot be blank.")
    private String username;
}
