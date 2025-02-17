package org.web.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.web.dto.rol.RolDTO;
import org.web.validation.NotEmptySet;

import java.util.Set;


public class ActualizarUsuarioDTO {

    @NotNull(message = "El  id es obligatorio")
    private Long id;

    @NotNull(message = "El username es obligatorio")
    private String username;

    @NotNull(message = "El password es obligatorio")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido") // Added email validation
    private String email;

    @NotEmptySet(message = "Debe haber al menos un rol asignado")
    private Set<RolDTO> roles;
}
