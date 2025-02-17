package org.web.test.rol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;
import org.web.usecase.rol.ActualizarRolUseCaseImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ActualizarRolServiceTest {


    @Mock
    private RolRepository rolRepository;


    @Mock
    private RolMapper rolMapper;

    @InjectMocks
    private ActualizarRolUseCaseImpl actualizarRolUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_Success() {
        // Datos de prueba
        int rolId = 1;
        ActualizarRolDTO actualizarRolDTO = new ActualizarRolDTO();
        actualizarRolDTO.setNombre("Admin");

        Rol rolExistente = new Rol();
        rolExistente.setId(rolId);
        rolExistente.setNombre("Consultor");

        Rol rolActualizado = new Rol();
        rolActualizado.setId(rolId);
        rolActualizado.setNombre("Admin2");

        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(rolId);
        rolDTO.setNombre("Gestor");

        // Simulaciones con Mockito
        when(rolRepository.findById(rolId)).thenReturn(Optional.of(rolExistente));
        doNothing().when(rolMapper).updateRolFromDTO(actualizarRolDTO, rolExistente);
        when(rolRepository.save(rolExistente)).thenReturn(rolActualizado);
        when(rolMapper.toDTO(rolActualizado)).thenReturn(rolDTO);

        // Ejecutar el caso de uso
        RolDTO resultado = actualizarRolUseCase.execute(rolId, actualizarRolDTO);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals("Gestor", resultado.getNombre());
        verify(rolRepository, times(1)).findById(rolId);
        verify(rolMapper, times(1)).updateRolFromDTO(actualizarRolDTO, rolExistente);
        verify(rolRepository, times(1)).save(rolExistente);
        verify(rolMapper, times(1)).toDTO(rolActualizado);
    }

}
