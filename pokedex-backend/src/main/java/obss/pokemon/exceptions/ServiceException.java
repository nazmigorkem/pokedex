package obss.pokemon.exceptions;

public class ServiceException extends RuntimeException {
    private ServiceException(String message) {
        super(message);
    }

    public static ServiceException PokemonWithNameNotFound(String pokemonName) {
        return new ServiceException(String.format("A Pokemon with the name %s was not found.", pokemonName));
    }

    public static ServiceException PokemonWithNameAlreadyExists(String pokemonName) {
        return new ServiceException(String.format("A Pokemon with the name %s already exists.", pokemonName));
    }

    public static ServiceException PokemonTypeWithNameAlreadyExists(String pokemonTypeName) {
        return new ServiceException(String.format("A Pokemon type with the name %s already exists.", pokemonTypeName));
    }

    public static ServiceException PokemonTypeWithNameNotFound(String pokemonTypeName) {
        return new ServiceException(String.format("A Pokemon type with the name %s was not found.", pokemonTypeName));
    }

    public static ServiceException UserWithNameAlreadyExists(String username) {
        return new ServiceException(String.format("A user with the username %s already exists.", username));
    }

    public static ServiceException UserWithUsernameNotFound(String username) {
        return new ServiceException(String.format("A user with the username %s was not found.", username));
    }

    public static ServiceException PokemonNotInCatchList(String pokemonName) {
        return new ServiceException(String.format("A Pokemon with the name %s is not in the catch list.", pokemonName));
    }

    public static ServiceException PokemonNotInWishList(String pokemonName) {
        return new ServiceException(String.format("A Pokemon with the name %s is not in the wish list.", pokemonName));
    }
}
