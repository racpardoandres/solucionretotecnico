package org.web.usecase.rol;


import org.springframework.stereotype.Component;
import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;
import org.web.inputport.rol.CreateRolUseCase;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;

@Component
public class CrearRolUseCaseImpl implements CreateRolUseCase {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public CrearRolUseCaseImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }


    @Override
    public RolDTO execute(CrearRolDTO crearRolDTO) {
        Rol rol = rolMapper.toEntity(crearRolDTO); // Mapea de DTO a entidad
        Rol rolCreado = rolRepository.save(rol); // Guarda la entidad en la base de datos
        return rolMapper.toDTO(rolCreado); // Mapea de entidad a DTO y lo devuelve
    }
}
