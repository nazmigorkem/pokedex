package obss.pokemon.repository;

import obss.pokemon.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameIgnoreCase(String username);

    Optional<User> findByUsernameIgnoreCase(String username);

    Page<User> findByUsernameStartsWithIgnoreCase(String username, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u JOIN u.catchList c WHERE u.username = ?1 AND c.name = ?2")
    boolean isPokemonInCatchList(String username, String pokemonName);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u JOIN u.wishlist w WHERE u.username = ?1 AND w.name = ?2")
    boolean isPokemonInWishList(String username, String pokemonName);
}
