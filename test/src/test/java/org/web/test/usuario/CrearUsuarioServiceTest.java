package org.web.test.usuario;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;
import org.web.mapper.UsuarioMapper;
import org.web.repository.PasswordEncodeRepository;
import org.web.repository.UsuarioRepository;
import org.web.usecase.usuario.CrearUsuarioUseCaseImpl;

class CrearUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PasswordEncodeRepository passwordEncodeRepository;

    @InjectMocks
    private CrearUsuarioUseCaseImpl crearUsuarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Datos de entrada
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO();
        crearUsuarioDTO.setUsername("testUser");
        crearUsuarioDTO.setPassword("password123");

        // Datos simulados
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setUsername("testUser");
        usuarioMock.setPassword("encryptedPassword");

        UsuarioDTO usuarioDTOMock = new UsuarioDTO();
        usuarioDTOMock.setId(1L);
        usuarioDTOMock.setUsername("testUser");

        // Simulación de comportamiento
        when(passwordEncodeRepository.encriptar(anyString())).thenReturn("encryptedPassword");
        when(usuarioMapper.toEntity(any(CrearUsuarioDTO.class))).thenReturn(usuarioMock);
        when(usuarioRepository.save(any())).thenReturn(usuarioMock);
        when(usuarioMapper.toDTO(any())).thenReturn(usuarioDTOMock);

        // Ejecutar método
        UsuarioDTO result = crearUsuarioUseCase.execute(crearUsuarioDTO);

        // Validaciones
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals(1L, result.getId());

        // Verificar llamadas a mocks
        verify(passwordEncodeRepository, times(1)).encriptar(anyString());
        verify(usuarioMapper, times(1)).toEntity(any(CrearUsuarioDTO.class));
        verify(usuarioRepository, times(1)).save(any());
        verify(usuarioMapper, times(1)).toDTO(any());
    }
}
