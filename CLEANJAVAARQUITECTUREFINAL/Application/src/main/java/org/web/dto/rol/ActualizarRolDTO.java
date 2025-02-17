package org.web.dto.rol;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class ActualizarRolDTO {

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

    @NotNull(message = "El  valor es obligatorio")
    private int id; // Necesario para identificar el rol a actualizar

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

}
