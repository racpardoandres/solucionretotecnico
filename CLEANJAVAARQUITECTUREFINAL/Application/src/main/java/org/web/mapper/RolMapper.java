package org.web.mapper;


import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;

import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface RolMapper {

     @Mapping(target = "id", ignore = true) // Ignoramos el ID porque CrearRolDTO no lo tiene
    @Mapping(target = "usuarios", ignore = true) // Ignoramos la lista de usuarios
    Rol toEntity(CrearRolDTO crearRolDTO);

    Rol updateRolFromDTO(ActualizarRolDTO actualizarRolDTO);

    RolDTO toDTO(Rol rol);


    //@Mapping(target = "id", ignore = true) // Ignora el campo id en la actualización
    void updateRolFromDTO(ActualizarRolDTO actualizarRolDTO,
                          @MappingTarget Rol rol);


    // Método para mapear Set<Rol> a Set<RolDTO>
    // Método para mapear una lista de Roles a una lista de RolDTOs
    @IterableMapping(elementTargetType = RolDTO.class)
    List<RolDTO> mapRolesToRolDTOs(List<Rol> roles);
}
