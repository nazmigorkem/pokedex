package obss.pokemon.model.pokemon;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PokemonUpdateRequestDTO {
    @NotBlank(message = "Search name cannot be blank.")
    private String searchName;


    private String name;

    private Set<String> types;

    private String imageUrl;

    private String description;

    @Min(value = 0, message = "Health cannot be less than 1.")
    private Integer health;

    @Min(value = 0, message = "Attack cannot be less than 1.")
    private Integer attack;

    @Min(value = 0, message = "Defense cannot be less than 1.")
    private Integer defense;

    @Min(value = 0, message = "Special attack cannot be less than 1.")
    private Integer specialAttack;

    @Min(value = 0, message = "Special defense cannot be less than 1.")
    private Integer specialDefense;

    @Min(value = 0, message = "Speed cannot be less than 1.")
    private Integer speed;
}
