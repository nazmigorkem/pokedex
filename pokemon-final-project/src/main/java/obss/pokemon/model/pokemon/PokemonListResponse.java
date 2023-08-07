package obss.pokemon.model.pokemon;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PokemonListResponse {
    private PokemonResponse pokemon;
    private long count;
}
