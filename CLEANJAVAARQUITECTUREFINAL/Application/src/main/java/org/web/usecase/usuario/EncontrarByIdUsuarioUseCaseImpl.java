package org.web.usecase.usuario;


import org.springframework.stereotype.Service;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;
import org.web.excepciones.BussinessNotFoundException;
import org.web.inputport.usuario.FindByUsuarioUseCase;
import org.web.mapper.UsuarioMapper;
import org.web.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class EncontrarByIdUsuarioUseCaseImpl implements FindByUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public EncontrarByIdUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public Optional<UsuarioDTO> execute(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BussinessNotFoundException("Rol no encontrado con ID: " + id));

        // 2. Mapear la entidad a un DTO
        return Optional.ofNullable(usuarioMapper.toDTO(usuario));
    }
}
