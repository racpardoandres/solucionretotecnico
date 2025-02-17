package org.web.usecase.usuario;


import org.springframework.stereotype.Service;
import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;
import org.web.inputport.usuario.CreateUsuarioUseCase;
import org.web.mapper.UsuarioMapper;
import org.web.repository.PasswordEncodeRepository;
import org.web.repository.UsuarioRepository;

@Service
public class CrearUsuarioUseCaseImpl implements CreateUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    private final PasswordEncodeRepository passwordEncodeRepository; // Inyección

    public CrearUsuarioUseCaseImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncodeRepository passwordEncodeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncodeRepository = passwordEncodeRepository;
    }

    @Override
    public UsuarioDTO execute(CrearUsuarioDTO crearUsuarioDTO) {
        String passwordEncriptado = passwordEncodeRepository.encriptar(crearUsuarioDTO.getPassword());
        crearUsuarioDTO.setPassword(passwordEncriptado);
        Usuario usuario = usuarioMapper.toEntity(crearUsuarioDTO); // Use injected Mapper
         Usuario usuarioCreado = usuarioRepository.save(usuario); // Guarda la entidad en la base de datos
        UsuarioDTO user = usuarioMapper.toDTO(usuarioCreado);

        return user;// Mapea de entidad a DTO y lo devuelve
    }
}
