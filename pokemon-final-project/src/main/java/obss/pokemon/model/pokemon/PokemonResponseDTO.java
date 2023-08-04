package obss.pokemon.model.pokemon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import obss.pokemon.model.pokemonType.PokemonTypeResponseDTO;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PokemonResponseDTO {
    private String name;
    private Set<PokemonTypeResponseDTO> types;
    private String imageUrl;
    private String description;
    private int health;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
}
