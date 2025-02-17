package org.web.test.rol;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web.entities.Rol;
import org.web.entities.Usuario;
import org.web.interfaces.UsuarioJpaRepository;
import org.web.jpa.UsuarioJPA;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;
import org.web.interfaces.UsuarioJpaRepository;
import org.web.repository.UserJpaRepositoryImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RolJpaCreateTest {


    @Mock
    private UsuarioJpaRepository usuarioJpaRepository;

    @Mock
    private UsuarioEntityMapper usuarioEntityMapper;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private UserJpaRepositoryImpl userJpaRepository;


    @Test
    void save_newUser() {
        // Arrange
        Usuario usuario = new Usuario();
        UsuarioJPA usuarioJPA = new UsuarioJPA();
        UsuarioJPA savedUsuarioJPA = new UsuarioJPA();
        savedUsuarioJPA.setId(1L);
        Usuario savedUsuario = new Usuario();
        savedUsuario.setId(1L);
        Rol rol = new Rol();
        rol.setNombre("tes");
        rol.setEstado(true);
        rol.setId(1);

        Set<Rol> rols = new HashSet<>();
        rols.add(rol);
        usuario.setRoles(rols);

        when(usuarioEntityMapper.toEntity(any(Usuario.class))).thenReturn(usuarioJPA);
        when(usuarioJpaRepository.save(any(UsuarioJPA.class))).thenReturn(savedUsuarioJPA);
        when(usuarioEntityMapper.toDomain(savedUsuarioJPA)).thenReturn(savedUsuario);

        // Act
        Usuario result = userJpaRepository.save(usuario);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(usuarioEntityMapper, times(1)).toEntity(any(Usuario.class));
        verify(usuarioJpaRepository, times(1)).save(any(UsuarioJPA.class));

     }

}
