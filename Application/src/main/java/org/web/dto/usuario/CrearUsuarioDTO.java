package org.web.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.web.dto.rol.RolDTO;
import org.web.validation.NotEmptySet;

import java.util.Set;


public class CrearUsuarioDTO {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolDTO> roles) {
        this.roles = roles;
    }

    @NotNull(message = "El username es obligatorio")
    @Size(min = 3, max = 50, message = "El username debe tener entre 3 y 50 caracteres")
    private String username;

    @NotNull(message = "El password es obligatorio")
    @Size(min = 8, message = "El password debe tener al menos 8 caracteres")
    private String password;


    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido") // Added email validation
    private String email;

    @NotEmptySet(message = "Debe haber al menos un rol asignado")
   private Set<RolDTO> roles;
}
