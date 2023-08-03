package obss.pokemon.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PokemonSaveRequestDTO {
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotNull(message = "Types cannot be blank.")
    private Set<String> types;

    @NotBlank(message = "Image url cannot be blank.")
    private String imageUrl;

    @NotBlank(message = "Description cannot be blank.")
    private String description;

    @NotNull(message = "Health cannot be blank.")
    @Min(value = 1, message = "Health cannot be less than 1.")
    private int health;

    @Min(value = 1, message = "Attack cannot be less than 1.")
    @NotNull(message = "Attack cannot be blank.")
    private int attack;

    @NotNull(message = "Defense cannot be blank.")
    @Min(value = 1, message = "Defense cannot be less than 1.")
    private int defense;

    @NotNull(message = "Special attack cannot be blank.")
    @Min(value = 1, message = "Special attack cannot be less than 1.")
    private int specialAttack;

    @NotNull(message = "Special defense cannot be blank.")
    @Min(value = 1, message = "Special defense cannot be less than 1.")
    private int specialDefense;

    @NotNull(message = "Speed cannot be blank.")
    @Min(value = 1, message = "Speed cannot be less than 1.")
    private int speed;
}
