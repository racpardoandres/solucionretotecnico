package org.web.inputport.rol;

import org.web.dto.rol.RolDTO;

import java.util.Optional;

public interface FindByIdRolUseCase {

    Optional<RolDTO> execute(int id);
}
