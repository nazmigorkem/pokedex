package obss.pokemon.repository;

import obss.pokemon.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> getPokemonByNameStartsWith(String name);
}
