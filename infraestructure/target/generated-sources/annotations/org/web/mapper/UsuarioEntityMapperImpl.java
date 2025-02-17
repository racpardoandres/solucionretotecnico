package org.web.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web.entities.Rol;
import org.web.entities.Usuario;
import org.web.jpa.RolEntityJPA;
import org.web.jpa.UsuarioJPA;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T22:56:16-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioEntityMapperImpl implements UsuarioEntityMapper {

    @Autowired
    private RolEntityMapper rolEntityMapper;

    @Override
    public UsuarioJPA toEntity(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioJPA usuarioJPA = new UsuarioJPA();

        usuarioJPA.setRoles( mapRoles( usuario.getRoles() ) );
        usuarioJPA.setId( usuario.getId() );
        usuarioJPA.setEmail( usuario.getEmail() );
        usuarioJPA.setPassword( usuario.getPassword() );
        usuarioJPA.setUsername( usuario.getUsername() );

        return usuarioJPA;
    }

    @Override
    public Usuario toDomain(UsuarioJPA entity) {
        if ( entity == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( entity.getId() );
        usuario.setUsername( entity.getUsername() );
        usuario.setPassword( entity.getPassword() );
        usuario.setEmail( entity.getEmail() );
        usuario.setRoles( rolEntityJPASetToRolSet( entity.getRoles() ) );

        return usuario;
    }

    @Override
    public List<Usuario> toDomainList(List<UsuarioJPA> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( entities.size() );
        for ( UsuarioJPA usuarioJPA : entities ) {
            list.add( toDomain( usuarioJPA ) );
        }

        return list;
    }

    @Override
    public RolEntityJPA toRolEntityJPA(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolEntityJPA rolEntityJPA = new RolEntityJPA();

        rolEntityJPA.setId( rol.getId() );
        rolEntityJPA.setNombre( rol.getNombre() );
        rolEntityJPA.setEstado( rol.isEstado() );
        rolEntityJPA.setUsuarios( usuarioListToUsuarioJPASet( rol.getUsuarios() ) );

        return rolEntityJPA;
    }

    protected Set<Rol> rolEntityJPASetToRolSet(Set<RolEntityJPA> set) {
        if ( set == null ) {
            return null;
        }

        Set<Rol> set1 = new LinkedHashSet<Rol>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RolEntityJPA rolEntityJPA : set ) {
            set1.add( rolEntityMapper.toDomain( rolEntityJPA ) );
        }

        return set1;
    }

    protected Set<UsuarioJPA> usuarioListToUsuarioJPASet(List<Usuario> list) {
        if ( list == null ) {
            return null;
        }

        Set<UsuarioJPA> set = new LinkedHashSet<UsuarioJPA>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Usuario usuario : list ) {
            set.add( toEntity( usuario ) );
        }

        return set;
    }
}
