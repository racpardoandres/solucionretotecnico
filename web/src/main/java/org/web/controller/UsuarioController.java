package org.web.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web.dto.usuario.ActualizarUsuarioDTO;
import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.inputport.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "usuarios", description = "Operaciones de autenticación")
public class UsuarioController {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;
    private final FindByUsuarioUseCase findByUsuarioUseCase;
    private final FindAllUsuarioUseCase findAllUsuarioUseCase;

    public UsuarioController(CreateUsuarioUseCase createUsuarioUseCase,

                             UpdateUsuarioUseCase updateUsuarioUseCase,
                             DeleteUsuarioUseCase deleteUsuarioUseCase,
                             FindByUsuarioUseCase findByUsuarioUseCase, FindAllUsuarioUseCase findAllUsuarioUseCase
    ) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
        this.findByUsuarioUseCase = findByUsuarioUseCase;
        this.findAllUsuarioUseCase = findAllUsuarioUseCase;
    }
    @PostMapping("/crear")
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema.")
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        UsuarioDTO usuarioCreado = createUsuarioUseCase.execute(crearUsuarioDTO);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @PutMapping("actualizarUsuario/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza la información de un usuario existente.")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable int id, @Valid @RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO) {
        UsuarioDTO usuarioActualizado = updateUsuarioUseCase.execute(actualizarUsuarioDTO, id);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @GetMapping("encontrar/{id}")
    @Operation(summary = "Encontrar un usuario por ID", description = "Busca un usuario por su identificador único.")
    public ResponseEntity<UsuarioDTO> encontrarUsuario(@PathVariable int id) {
        Optional<UsuarioDTO> usuarioDTO = findByUsuarioUseCase.execute(id);
        return usuarioDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("deleteUsuario/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema.")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        deleteUsuarioUseCase.excute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("encontrarTODOS")
    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    public ResponseEntity<List<UsuarioDTO>> encontrarTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = findAllUsuarioUseCase.execute();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
