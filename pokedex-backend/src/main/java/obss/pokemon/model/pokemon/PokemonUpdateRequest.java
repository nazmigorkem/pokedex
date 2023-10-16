package obss.pokemon.model.pokemon;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PokemonUpdateRequest {
    @NotBlank(message = "Search name cannot be blank.")
    private String searchName;

    @Size(max = 255, message = "Name cannot be longer than 255 characters.")
    private String name;

    @NotNull(message = "Types cannot be blank.")
    @Size(min = 1, message = "Types cannot be empty.")
    private Set<String> types;

    @URL(message = "Image url must be a url from https://assets.pokemon.com.", host = "assets.pokemon.com")
    @NotBlank(message = "Image url cannot be blank.")
    @URL(message = "Image url must be a valid url.")
    @Size(max = 255, message = "Image url cannot be longer than 255 characters.")
    private String imageUrl;

    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 255, message = "Description cannot be longer than 1000 characters.")
    private String description;

    @NotNull(message = "Health cannot be blank.")
    @Min(value = 1, message = "Health cannot be less than 1.")
    @Max(value = 100, message = "Health cannot be greater than 100.")
    private int health;

    @NotNull(message = "Attack cannot be blank.")
    @Min(value = 1, message = "Attack cannot be less than 1.")
    @Max(value = 100, message = "Attack cannot be greater than 100.")
    private int attack;

    @NotNull(message = "Defense cannot be blank.")
    @Min(value = 1, message = "Defense cannot be less than 1.")
    @Max(value = 100, message = "Defense cannot be greater than 100.")
    private int defense;

    @NotNull(message = "Special attack cannot be blank.")
    @Min(value = 1, message = "Special attack cannot be less than 1.")
    @Max(value = 100, message = "Special attack cannot be greater than 100.")
    private int specialAttack;

    @NotNull(message = "Special defense cannot be blank.")
    @Min(value = 1, message = "Special defense cannot be less than 1.")
    @Max(value = 100, message = "Special defense cannot be greater than 100.")
    private int specialDefense;

    @NotNull(message = "Speed cannot be blank.")
    @Min(value = 1, message = "Speed cannot be less than 1.")
    @Max(value = 100, message = "Speed cannot be greater than 100.")
    private int speed;
}
