package org.web.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "rol")

public class RolEntityJPA {

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

    public Boolean  isEstado() {
        return estado;
    }

    public void setEstado(Boolean  estado) {
        this.estado = estado;
    }

    public Set<UsuarioJPA> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioJPA> usuarios) {
        this.usuarios = usuarios;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Use Long for consistency with other IDs

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Boolean  estado;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // Many-to-Many, mapped by "roles" in UsuarioJPA
    @JsonBackReference
    @JsonIgnore // You can keep this to avoid serializing the list of users from the role side
    private Set<UsuarioJPA> usuarios; // Use Set for consistency with UsuarioJPA


}
