package org.web.dto.login;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "El nombre de usuario no puede estar vacío") // Anotación para username
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía") // Anotación para password
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
}
