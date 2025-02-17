package org.web.mapper;


import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.web.dto.usuario.ActualizarUsuarioDTO;
import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Usuario;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "id", ignore = true) // Ignoramos el ID porque CrearUsuarioDTO no lo tiene
    Usuario toEntity(CrearUsuarioDTO crearUsuarioDTO);

    @Mapping(target = "id", source = "id") // Or ignore if ID is not updatable
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    Usuario toEntity(ActualizarUsuarioDTO actualizarUsuarioDTO);

    @Mapping(source = "id", target = "id")
    UsuarioDTO toDTO(Usuario usuario);


    List<UsuarioDTO> mapUsuarioToRolDTOs(List<Usuario> usuarios);

}
