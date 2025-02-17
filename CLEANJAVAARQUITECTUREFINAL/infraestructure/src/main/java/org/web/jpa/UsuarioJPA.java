package org.web.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "usuario")
public class UsuarioJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public Set<RolEntityJPA> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntityJPA> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.LAZY) // Many-to-Many
    @JoinTable(
            name = "usuario_rol", // Join table name
            joinColumns = @JoinColumn(name = "usuario_id"), // Foreign key in usuario_rol table referencing usuario
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Foreign key in usuario_rol table referencing rol
    )
    @JsonManagedReference
    @ToString.Exclude //
    @JsonIgnoreProperties("usuarios")
    private Set<RolEntityJPA> roles; // Use Set for Many-to-Many


}
