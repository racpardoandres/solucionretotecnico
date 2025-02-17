package org.web.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.web.controller.RolController;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.inputport.rol.CreateRolUseCase;
import org.web.inputport.rol.DeleteRolUseCase;
import org.web.inputport.rol.UpdateRolUseCase;
import org.web.usecase.rol.EncontrarByIdRolUseCaseImpl;
import org.web.usecase.rol.ListarTodosRolUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RolControllerTest
{

    @Mock
    private CreateRolUseCase createRolUseCase;

    @Mock
    private UpdateRolUseCase updateRolUseCase;

    @Mock
    private DeleteRolUseCase deleteRolUseCase;

    @Mock
    private EncontrarByIdRolUseCaseImpl encontrarByIdRolUseCase;

    @Mock
    private ListarTodosRolUseCase listarTodosRolUseCase;

    @InjectMocks
    private RolController rolController;

    @Test
    public void testEncontrarRol() {
        int id = 1;
        RolDTO rolDTO = new RolDTO();
        Optional<RolDTO> optionalRolDTO = Optional.of(rolDTO);

        when(encontrarByIdRolUseCase.execute(id)).thenReturn(optionalRolDTO);

        ResponseEntity<RolDTO> response = rolController.encontrarRol(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolDTO, response.getBody());
    }

    @Test
    public void testEncontrarTodosLosRoles() {

        ArrayList<RolDTO> rolDTOList = new ArrayList<>(Collections.singletonList(new RolDTO()));
        when(listarTodosRolUseCase.execute()).thenReturn(rolDTOList);

        ResponseEntity<List<RolDTO>> response = rolController.encontrarTodosLosRoles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolDTOList, response.getBody());
    }

    @Test
    public void testDeleteRol() {
        int id = 1;

        ResponseEntity<String> response = rolController.deleteRol(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rol Eliminado", response.getBody());
        verify(deleteRolUseCase, times(1)).excute(id); // Verify that the use case was called
    }

    @Test
    public void testGuardarRol() {
        CrearRolDTO crearRolDTO = new CrearRolDTO();
        RolDTO rolDTO = new RolDTO();

        when(createRolUseCase.execute(crearRolDTO)).thenReturn(rolDTO);

        ResponseEntity<RolDTO> response = rolController.guardarRol(crearRolDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(rolDTO, response.getBody());
    }

    @Test
    public void testActualizarRol() {


        int id = 1;
        ActualizarRolDTO actualizarRolDTO = new ActualizarRolDTO();
        RolDTO rolDTO = new RolDTO();

        when(updateRolUseCase.execute(id, actualizarRolDTO)).thenReturn(rolDTO);

        ResponseEntity<RolDTO> response = rolController.actualizarRol(id, actualizarRolDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rolDTO, response.getBody());
    }

}
