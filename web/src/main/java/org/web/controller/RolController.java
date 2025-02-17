package org.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.inputport.rol.CreateRolUseCase;
import org.web.inputport.rol.DeleteRolUseCase;
import org.web.inputport.rol.UpdateRolUseCase;
import org.web.usecase.rol.EncontrarByIdRolUseCaseImpl;
import org.web.usecase.rol.ListarTodosRolUseCase;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@Tag(name = "roles", description = "Operaciones de roles")
public class RolController {

    private final CreateRolUseCase createRolUseCase;
    private final UpdateRolUseCase updateRolUseCase;
    private  final DeleteRolUseCase deleteRolUseCase;
    private final EncontrarByIdRolUseCaseImpl encontrarByIdRolUseCase;
    private final ListarTodosRolUseCase listarTodosRolUseCase;



    public RolController(CreateRolUseCase createRolUseCase,
                         UpdateRolUseCase updateRolUseCase,
                         DeleteRolUseCase deleteRolUseCase,
                         EncontrarByIdRolUseCaseImpl encontrarByIdRolUseCase,
                         ListarTodosRolUseCase listarTodosRolUseCase
                     ) {
        this.createRolUseCase = createRolUseCase;
        this.updateRolUseCase = updateRolUseCase;
        this.deleteRolUseCase = deleteRolUseCase;
        this.encontrarByIdRolUseCase = encontrarByIdRolUseCase;
        this.listarTodosRolUseCase = listarTodosRolUseCase;

    }


    @PostMapping("guardarrol")
    @Operation(summary = "Crear un nuevo rol",
            description = "Crea un nuevo rol en el sistema.")
    public ResponseEntity<RolDTO> guardarRol(@Valid @RequestBody CrearRolDTO crearRolDTO) {
        RolDTO rolCreado = createRolUseCase.execute(crearRolDTO); // Llama al caso de uso
        return new ResponseEntity<>(rolCreado, HttpStatus.CREATED); // Devuelve el DTO
    }

    @PutMapping("actualizarRol/{id}")
    @Operation(summary = "Actualizar un rol",
            description = "Actualiza la información de un rol existente.")

    public ResponseEntity<RolDTO> actualizarRol(@PathVariable int id, @Valid @RequestBody ActualizarRolDTO actualizarRolDTO) {
        RolDTO rolActualizado = updateRolUseCase.execute(id, actualizarRolDTO); // Llama al caso de uso
        return new ResponseEntity<>(rolActualizado, HttpStatus.OK); // Devuelve el DTO
    }



    @DeleteMapping("deleteRol/{id}")
    @Operation(summary = "Eliminar un rol", description = "Elimina un rol del sistema.")
    public ResponseEntity<String> deleteRol(@PathVariable int id) {
        deleteRolUseCase.excute(id);
        return new ResponseEntity<>("Rol Eliminado", HttpStatus.OK);
    }


    @GetMapping("encontrar/{id}")
    @Operation(summary = "Encontrar un rol por ID", description = "Busca un usuario por su identificador único.")
    public ResponseEntity<RolDTO> encontrarRol(@PathVariable int id) {
        Optional<RolDTO> rol = encontrarByIdRolUseCase.execute(id);  // Llama al caso de uso
        return new ResponseEntity<>(rol.get(), HttpStatus.OK); // Devuelve el DTO
    }

    @GetMapping("encontrarTODOS")
    @Operation(summary = "Encontrar todos los roles", description = "Returna roles") // Recommende
    public ResponseEntity<List<RolDTO>> encontrarTodosLosRoles() {
        List<RolDTO> roles = listarTodosRolUseCase.execute(); // Llama al caso de uso
        return new ResponseEntity<>(roles, HttpStatus.OK); // Devuelve la lista de DTOs
    }

}
