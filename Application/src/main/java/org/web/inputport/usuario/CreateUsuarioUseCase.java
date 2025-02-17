package org.web.inputport.usuario;


import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;

public interface CreateUsuarioUseCase {

    UsuarioDTO execute(CrearUsuarioDTO crearUsuarioDTO);
}
