package org.web.usecase.rol;

import org.springframework.stereotype.Component;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;
import org.web.excepciones.BussinessNotFoundException;
import org.web.inputport.rol.UpdateRolUseCase;
import org.web.mapper.RolMapper;
import org.web.repository.RolRepository;

@Component
public class ActualizarRolUseCaseImpl implements UpdateRolUseCase {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;




    public ActualizarRolUseCaseImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }



    @Override
    public RolDTO execute(int id, ActualizarRolDTO actualizarRolDTO) {
        // 1. Obtener el rol existente (puedes lanzar una excepción si no existe)
        Rol rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new BussinessNotFoundException("Rol no encontrado con ID: " + id));

        // 2. Mapear los datos del DTO a la entidad existente
        rolMapper.updateRolFromDTO(actualizarRolDTO, rolExistente);

        // 3. Guardar la entidad actualizada en la base de datos
        Rol rolActualizado = rolRepository.save(rolExistente);

        // 4. Mapear la entidad actualizada a un DTO y devolverlo
        return rolMapper.toDTO(rolActualizado);
    }
}
