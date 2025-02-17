package org.web.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.web.entities.Rol;
import org.web.jpa.RolEntityJPA;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T22:56:15-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RolEntityMapperImpl implements RolEntityMapper {

    @Override
    public RolEntityJPA toEntity(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolEntityJPA rolEntityJPA = new RolEntityJPA();

        rolEntityJPA.setId( rol.getId() );
        rolEntityJPA.setNombre( rol.getNombre() );
        rolEntityJPA.setEstado( rol.isEstado() );

        return rolEntityJPA;
    }

    @Override
    public Rol toDomain(RolEntityJPA entity) {
        if ( entity == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setId( entity.getId() );
        rol.setNombre( entity.getNombre() );
        if ( entity.isEstado() != null ) {
            rol.setEstado( entity.isEstado() );
        }

        return rol;
    }

    @Override
    public List<Rol> toDomainList(List<RolEntityJPA> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Rol> list = new ArrayList<Rol>( entities.size() );
        for ( RolEntityJPA rolEntityJPA : entities ) {
            list.add( toDomain( rolEntityJPA ) );
        }

        return list;
    }
}
