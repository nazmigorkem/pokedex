package tech.obss.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDTO {
    @NotBlank(message = "Please enter a password.")
    @Size(min = 3, max = 255, message = "Password must be between 3 and 255 characters.")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UpdateUserRequestDTO{" +
                "password='" + password + '\'' +
                '}';
    }
}
