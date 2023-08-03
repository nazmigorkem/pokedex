package obss.pokemon.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonTypeSaveRequestDTO {
    @Size(min = 2, max = 20)
    @NotBlank(message = "Name cannot be blank.")
    private String name;
}
