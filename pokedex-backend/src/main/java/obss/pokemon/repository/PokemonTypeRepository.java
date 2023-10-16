package obss.pokemon.repository;

import obss.pokemon.entity.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonType, Long> {

    List<PokemonType> findPokemonTypeByNameStartsWithIgnoreCase(String name);

    Optional<PokemonType> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
