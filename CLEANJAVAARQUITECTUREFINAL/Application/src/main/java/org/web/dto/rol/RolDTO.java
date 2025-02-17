package org.web.dto.rol;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.web.dto.usuario.UsuarioDTO;

import java.util.List;


public class RolDTO {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @JsonIgnore // Add this annotation
    private List<UsuarioDTO> usuarios;
}
