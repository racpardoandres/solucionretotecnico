package org.web.inputport.usuario;


import org.web.dto.usuario.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public interface FindAllUsuarioUseCase {
    List<UsuarioDTO> execute();
}
