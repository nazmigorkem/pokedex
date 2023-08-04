package obss.pokemon.model.pokemonType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonTypeSaveRequest {
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters.")
    @NotBlank(message = "Name cannot be blank.")
    private String name;
}
