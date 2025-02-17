package org.web.inputport.usuario;


import org.web.dto.usuario.UsuarioDTO;

import java.util.Optional;

public interface FindByUsuarioUseCase {

    Optional<UsuarioDTO> execute(int id);
}
