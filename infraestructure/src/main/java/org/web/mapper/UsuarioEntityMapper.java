package org.web.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.web.entities.Rol;
import org.web.entities.Usuario;
import org.web.jpa.RolEntityJPA;
import org.web.jpa.UsuarioJPA;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RolEntityMapper.class)
public interface UsuarioEntityMapper {


    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles") // Add this for roles
    @Mapping(target = "id", source = "id") // Mapea el ID directamente
   UsuarioJPA toEntity(Usuario usuario);

    Usuario toDomain(UsuarioJPA entity);

    List<Usuario> toDomainList(List<UsuarioJPA> entities);

    @Named("mapRoles")  // This handles the roles mapping
    default Set<RolEntityJPA> mapRoles(Set<Rol> roles) {
        if (roles == null) {
            return null; // Or return an empty set: Collections.emptySet(); if null is not allowed
        }
        return roles.stream()
                .map(this::toRolEntityJPA) // Use the correct mapping method
                .collect(Collectors.toSet());
    }

    RolEntityJPA toRolEntityJPA(Rol rol); // You'll need this method to map a single role

}
