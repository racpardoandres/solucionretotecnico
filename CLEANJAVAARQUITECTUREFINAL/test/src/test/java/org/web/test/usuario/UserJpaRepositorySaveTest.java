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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserJpaRepositorySaveTest {


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
    void testSave_NewUser() {
        // Configurar comportamiento de los mocks
        when(usuarioEntityMapper.toEntity(usuario)).thenReturn(usuarioJPA);
        when(rolEntityMapper.toEntity(rol)).thenReturn(rolEntityJPA);
        when(usuarioRepository.save(any(UsuarioJPA.class))).thenReturn(usuarioJPA);
        when(usuarioEntityMapper.toDomain(usuarioJPA)).thenReturn(usuario);

        // Ejecutar el método a probar
        Usuario savedUser = userJpaRepository.save(usuario);

        // Verificaciones
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());

        verify(usuarioRepository).save(any(UsuarioJPA.class));
        verify(usuarioEntityMapper).toDomain(usuarioJPA);
    }

}
