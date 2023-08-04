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

    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;

    @ManyToMany(mappedBy = "usersWishList")
    private List<Pokemon> wishlist;


    @ManyToMany(mappedBy = "usersCatchList")
    private List<Pokemon> catchList;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles;
}
