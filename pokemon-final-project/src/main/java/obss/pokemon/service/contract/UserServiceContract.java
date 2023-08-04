package obss.pokemon.service.contract;

import obss.pokemon.model.user.UserSaveRequestDTO;
import obss.pokemon.model.user.UserSaveResponseDTO;

public interface UserServiceContract {
    UserSaveResponseDTO addUser(UserSaveRequestDTO userSaveRequestDTO);


}
