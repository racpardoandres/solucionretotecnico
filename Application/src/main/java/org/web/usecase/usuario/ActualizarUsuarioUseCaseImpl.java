package org.web.usecase.usuario;


import org.springframework.stereotype.Service;
import org.web.dto.usuario.ActualizarUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;
import org.web.excepciones.BussinessNotFoundException;
import org.web.inputport.usuario.UpdateUsuarioUseCase;
import org.web.mapper.UsuarioMapper;
import org.web.repository.UsuarioRepository;

@Service
public class ActualizarUsuarioUseCaseImpl implements UpdateUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public ActualizarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO execute(ActualizarUsuarioDTO actualizarUsuarioDTO, int id) {
        // 1. Obtener el rol existente (puedes lanzar una excepción si no existe)
        Usuario rolExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BussinessNotFoundException("Usuario no encontrado con ID: " + id));

        // 3. Guardar la entidad actualizada en la base de datos
        Usuario usuarioActualizado = usuarioRepository.update(this.usuarioMapper.toEntity(actualizarUsuarioDTO)
        );

        // 4. Mapear la entidad actualizada a un DTO y devolverlo
        return usuarioMapper.toDTO(usuarioActualizado);
    }
}
