package org.web.test.rol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.web.dto.rol.RolDTO;
import org.web.dto.rol.CrearRolDTO;
import org.web.entities.Rol;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;
import org.web.usecase.rol.CrearRolUseCaseImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CrearRolServiceTest {

    @Mock
    private RolRepository rolRepository;


    @Mock
    private RolMapper rolMapper;

    @InjectMocks
    private CrearRolUseCaseImpl crearRolUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void CrearRolSucces() {

        // Datos de entrada
        CrearRolDTO crearRolDTO = new CrearRolDTO();
        crearRolDTO.setNombre("Admin");

        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("Admin");

        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(1);
        rolDTO.setNombre("Admin");

        // Configurar el comportamiento de los mocks
        when(rolMapper.toEntity(crearRolDTO)).thenReturn(rol);
        when(rolRepository.save(rol)).thenReturn(rol);
        when(rolMapper.toDTO(rol)).thenReturn(rolDTO);

        // Ejecutar el caso de uso
        RolDTO resultado = crearRolUseCase.execute(crearRolDTO);

        // Validar resultados
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Admin", resultado.getNombre());

        // Verificar que los métodos fueron llamados correctamente
        verify(rolMapper, times(1)).toEntity(crearRolDTO);
        verify(rolRepository, times(2)).save(rol);
        verify(rolMapper, times(1)).toDTO(rol);
    }
}
