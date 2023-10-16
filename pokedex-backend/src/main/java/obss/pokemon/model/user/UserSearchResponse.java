package obss.pokemon.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserSearchResponse {
    private String username;
    private List<String> roles;
}
