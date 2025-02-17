package org.web.usecase.rol;


import org.springframework.stereotype.Component;
import org.web.entities.Rol;
import org.web.dto.rol.RolDTO;
import org.web.excepciones.BussinessNotFoundException;
import org.web.inputport.rol.FindByIdRolUseCase;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;

import java.util.Optional;

@Component
public class EncontrarByIdRolUseCaseImpl implements FindByIdRolUseCase {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public EncontrarByIdRolUseCaseImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public Optional<RolDTO> execute(int id) {
        // 1. Obtener el rol desde el repositorio
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new BussinessNotFoundException("Rol no encontrado con ID: " + id));

        // 2. Mapear la entidad a un DTO
        return Optional.ofNullable(rolMapper.toDTO(rol));
    }
}
