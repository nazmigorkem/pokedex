package obss.pokemon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User extends EntityBase {
    @Column(name = "USERNAME", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "POKEMON_USER_WISHLIST",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")
    )
    private List<Pokemon> wishlist;


    @ManyToMany
    @JoinTable(name = "POKEMON_USER_CATCHLIST",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")
    )
    private List<Pokemon> catchList;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles;
}
