package org.web.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.web.entities.Rol;
import org.web.jpa.RolEntityJPA;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolEntityMapper {


    RolEntityMapper INSTANCE = Mappers.getMapper(RolEntityMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "usuarios", ignore = true) // Evita la recursión
    RolEntityJPA toEntity(Rol rol);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "usuarios", ignore = true) // Evita problemas de recursión
    Rol toDomain(RolEntityJPA entity);

    List<Rol> toDomainList(List<RolEntityJPA> entities);
}
