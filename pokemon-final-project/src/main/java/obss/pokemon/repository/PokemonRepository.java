package obss.pokemon.repository;

import obss.pokemon.entity.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    Page<Pokemon> getPokemonByNameStartsWithIgnoreCase(String name, Pageable pageable);

    boolean existsByNameIgnoreCase(String name);

    Optional<Pokemon> getPokemonByNameIgnoreCase(String name);

    Page<Pokemon> getAllByUsersCatchList_UsernameIgnoreCase(String username, Pageable pageable);

    Page<Pokemon> getAllByUsersWishList_UsernameIgnoreCase(String username, Pageable pageable);

    Page<Pokemon> getAllByTypes_NameStartsWithIgnoreCase(String type, Pageable pageable);

}
