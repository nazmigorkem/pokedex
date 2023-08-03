package obss.pokemon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "POKEMON")
public class Pokemon extends EntityBase {
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "HEALTH", nullable = false)
    private int health;

    @Column(name = "ATTACK", nullable = false)
    private int attack;

    @Column(name = "DEFENSE", nullable = false)
    private int defense;

    @Column(name = "SPEED", nullable = false)
    private int speed;

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private PokemonType type;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @ManyToMany
    @JoinTable(name = "POKEMON_USER",
            joinColumns = @JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    private Set<User> users;
}
