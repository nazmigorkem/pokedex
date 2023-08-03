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

        @OneToMany(mappedBy = "type")
        private Set<Pokemon> pokemons;
}
