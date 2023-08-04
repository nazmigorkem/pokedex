package obss.pokemon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "SPECIAL_ATTACK", nullable = false)
    private int specialAttack;

    @Column(name = "SPECIAL_DEFENSE", nullable = false)
    private int specialDefense;

    @Column(name = "DEFENSE", nullable = false)
    private int defense;

    @Column(name = "SPEED", nullable = false)
    private int speed;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @ManyToMany
    @JoinTable(name = "POKEMON_POKEMON_TYPE",
            joinColumns = @JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID"))
    private Set<PokemonType> types;


    @ManyToMany(mappedBy = "wishlist")
    private List<User> usersWishList;

    @ManyToMany(mappedBy = "catchList")
    private List<User> usersCatchList;
}
