package org.web.usecase.rol;


import org.springframework.stereotype.Component;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;
import org.web.inputport.rol.FindALLRolUseCase;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarTodosRolUseCase implements FindALLRolUseCase {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public ListarTodosRolUseCase(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public ArrayList<RolDTO> execute() {
        // 1. Obtener la lista de roles desde el repositorio
        ArrayList<Rol> roles = rolRepository.findALL();

        // 2. Mapear la lista de entidades a una lista de DTOs
        return (ArrayList<RolDTO>) rolMapper.mapRolesToRolDTOs(roles.stream().toList());
    }
}
