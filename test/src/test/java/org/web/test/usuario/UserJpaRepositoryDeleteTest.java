package org.web.test.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web.entities.Usuario;
import org.web.interfaces.UsuarioJpaRepository;
import org.web.jpa.UsuarioJPA;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;
import org.web.repository.UserJpaRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserJpaRepositoryDeleteTest {

    @Spy
    private UsuarioJpaRepository usuarioJpaRepository; // Now a spy

    @Mock
    private UsuarioEntityMapper usuarioEntityMapper;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private UserJpaRepositoryImpl userJpaRepository;

    @Test
    void DeleteUser() {
        // Arrange
        long userId = 1L;

        // Optional: You can still stub other methods if needed
        // when(usuarioJpaRepository.findById(userId)).thenReturn(Optional.of(new UsuarioJPA()));

        // Act
        userJpaRepository.deleteById(userId);

        // Assert
        verify(usuarioJpaRepository, times(1)).deleteById(userId); // Verify the call

    }
}
