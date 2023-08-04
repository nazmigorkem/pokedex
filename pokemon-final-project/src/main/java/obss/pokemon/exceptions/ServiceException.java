package obss.pokemon.exceptions;

public class ServiceException extends RuntimeException {
    private ServiceException(String message) {
        super(message);
    }

    public static ServiceException PokemonNotFound(String pokemonName) {
        return new ServiceException(String.format("Pokemon with name %s is not found.", pokemonName));
    }

    public static ServiceException PokemonWithNameAlreadyExists(String pokemonName) {
        return new ServiceException(String.format("Pokemon with name %s already exists.", pokemonName));
    }

    public static ServiceException PokemonTypeWithNameAlreadyExists(String pokemonTypeName) {
        return new ServiceException(String.format("Pokemon type with name %s already exists.", pokemonTypeName));
    }

    public static ServiceException PokemonTypeNotFound(String pokemonTypeName) {
        return new ServiceException(String.format("Pokemon type with name %s is not found.", pokemonTypeName));
    }

    public static ServiceException UserWithNameAlreadyExists(String username) {
        return new ServiceException(String.format("User with username %s already exists.", username));
    }
}
