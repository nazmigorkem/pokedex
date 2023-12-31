package obss.pokemon.model.pokemonType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PokemonTypeUpdateRequest {
    @Size(min = 2, max = 20, message = "Search name should be between 2 and 20 characters.")
    @NotBlank(message = "Search name cannot be blank.")
    private String searchName;

    @Size(min = 2, max = 20, message = "New name should be between 2 and 20 characters.")
    private String name;

    @Size(min = 7, max = 7, message = "New color should be 7 characters.")
    @NotBlank(message = "New color cannot be blank.")
    private String color;
}
