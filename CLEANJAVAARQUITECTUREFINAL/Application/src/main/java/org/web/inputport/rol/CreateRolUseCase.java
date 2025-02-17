package org.web.inputport.rol;


import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;

public interface CreateRolUseCase {

    RolDTO execute(CrearRolDTO crearRolDTO);
}
