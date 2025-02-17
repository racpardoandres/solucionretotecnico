package org.web.usecase.usuario;


import org.springframework.stereotype.Service;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;
import org.web.inputport.usuario.FindAllUsuarioUseCase;
import org.web.mapper.UsuarioMapper;
import org.web.repository.UsuarioRepository;


import java.util.List;

@Service
public class ListarTodosUsuarioUseCaseImpl implements FindAllUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public ListarTodosUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public List<UsuarioDTO> execute() {
        // 1. Obtener la lista de roles desde el repositorio
        List<Usuario> usuarios = usuarioRepository.findALL();

        // 2. Mapear la lista de entidades a una lista de DTOs
        List<UsuarioDTO> usuarioDTOList= usuarioMapper.mapUsuarioToRolDTOs(usuarios);

    return  usuarioDTOList;
    }
}
