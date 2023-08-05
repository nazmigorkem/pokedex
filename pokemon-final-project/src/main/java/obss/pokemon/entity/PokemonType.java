package obss.pokemon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "POKEMON_TYPE")
public class PokemonType extends EntityBase {
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Pokemon> pokemons;

    @PreRemove
    public void onPreRemove() {
        for (Pokemon pokemon : pokemons) {
            pokemon.getTypes().remove(this);
        }
    }

}
