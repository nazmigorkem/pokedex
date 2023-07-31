package tech.obss.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SaveUserRequestDTO {

    @NotBlank(message = "Please enter a username.")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters.")
    @Email(message = "Please enter a valid email address.")
    private String username;

    @NotBlank(message = "Please enter a password.")
    @Size(min = 3, max = 255, message = "Password must be between 3 and 255 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SaveUserRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
