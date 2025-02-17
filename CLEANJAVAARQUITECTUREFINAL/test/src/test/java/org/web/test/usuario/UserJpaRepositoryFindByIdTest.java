package org.web.test.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web.entities.Rol;
import org.web.entities.Usuario;
import org.web.interfaces.UsuarioJpaRepository;
import org.web.jpa.RolEntityJPA;
import org.web.jpa.UsuarioJPA;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;
import org.web.repository.UserJpaRepositoryImpl;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJpaRepositoryImplFindByIdTest {

    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @Mock
    private UsuarioEntityMapper usuarioEntityMapper;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private UserJpaRepositoryImpl userJpaRepository;

    private Usuario usuario;
    private UsuarioJPA usuarioJPA;
    private Rol rol;
    private RolEntityJPA rolEntityJPA;

    @BeforeEach
    void setUp() {
        // Crear datos de prueba
        rol = new Rol();
        rol.setId(6);
        rol.setNombre("Rol 1");
        rol.setEstado(true);
        rolEntityJPA = new RolEntityJPA();
        rolEntityJPA.setId(1);
        rolEntityJPA.setNombre("Rol 2");

        usuario = new Usuario();
        usuario.setId(null); // Nuevo usuario
        usuario.setUsername("testuser");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password123");
        usuario.setRoles(Set.of(rol));

        usuarioJPA = new UsuarioJPA();
        usuarioJPA.setId(1L);
        usuarioJPA.setUsername("testuser");
        usuarioJPA.setEmail("test@example.com");
        usuarioJPA.setPassword("password123");
        usuarioJPA.setRoles(Set.of(rolEntityJPA));
    }
    @Test
    void testFindById_UserExists() {
        when(usuarioRepository.findByIdWithRoles(1L)).thenReturn(Optional.of(usuarioJPA));
        when(usuarioEntityMapper.toDomain(usuarioJPA)).thenReturn(usuario);

        Optional<Usuario> result = userJpaRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
        verify(usuarioRepository, times(1)).findByIdWithRoles(1L);
        verify(usuarioEntityMapper, times(1)).toDomain(usuarioJPA);
    }




}
