package org.web.inputport.rol;


import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.RolDTO;

public interface UpdateRolUseCase {

    RolDTO execute(int id, ActualizarRolDTO actualizarRolDTO);
}
