package org.web.inputport.usuario;


import org.web.dto.usuario.ActualizarUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;

public interface UpdateUsuarioUseCase {

    UsuarioDTO execute(ActualizarUsuarioDTO actualizarUsuarioDTO, int id);
}
