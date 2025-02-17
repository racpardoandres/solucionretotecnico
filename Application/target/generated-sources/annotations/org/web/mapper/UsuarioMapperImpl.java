package org.web.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.web.dto.rol.RolDTO;
import org.web.dto.usuario.ActualizarUsuarioDTO;
import org.web.dto.usuario.CrearUsuarioDTO;
import org.web.dto.usuario.UsuarioDTO;
import org.web.entities.Rol;
import org.web.entities.Usuario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T22:56:07-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(CrearUsuarioDTO crearUsuarioDTO) {
        if ( crearUsuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsername( crearUsuarioDTO.getUsername() );
        usuario.setPassword( crearUsuarioDTO.getPassword() );
        usuario.setEmail( crearUsuarioDTO.getEmail() );
        usuario.setRoles( rolDTOSetToRolSet( crearUsuarioDTO.getRoles() ) );

        return usuario;
    }

    @Override
    public Usuario toEntity(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        if ( actualizarUsuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( actualizarUsuarioDTO.getId() );
        usuario.setUsername( actualizarUsuarioDTO.getUsername() );
        usuario.setPassword( actualizarUsuarioDTO.getPassword() );
        usuario.setEmail( actualizarUsuarioDTO.getEmail() );
        usuario.setRoles( rolDTOSetToRolSet( actualizarUsuarioDTO.getRoles() ) );

        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setUsername( usuario.getUsername() );
        usuarioDTO.setPassword( usuario.getPassword() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setRoles( rolSetToRolDTOSet( usuario.getRoles() ) );

        return usuarioDTO;
    }

    @Override
    public List<UsuarioDTO> mapUsuarioToRolDTOs(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toDTO( usuario ) );
        }

        return list;
    }

    protected Set<Rol> rolDTOSetToRolSet(Set<RolDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Rol> set1 = new LinkedHashSet<Rol>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RolDTO rolDTO : set ) {
            set1.add( rolDTOToRol( rolDTO ) );
        }

        return set1;
    }

    protected Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setUsername( usuarioDTO.getUsername() );
        usuario.setPassword( usuarioDTO.getPassword() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setRoles( rolDTOSetToRolSet( usuarioDTO.getRoles() ) );

        return usuario;
    }

    protected List<Usuario> usuarioDTOListToUsuarioList(List<UsuarioDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Usuario> list1 = new ArrayList<Usuario>( list.size() );
        for ( UsuarioDTO usuarioDTO : list ) {
            list1.add( usuarioDTOToUsuario( usuarioDTO ) );
        }

        return list1;
    }

    protected Rol rolDTOToRol(RolDTO rolDTO) {
        if ( rolDTO == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setId( rolDTO.getId() );
        rol.setNombre( rolDTO.getNombre() );
        if ( rolDTO.getEstado() != null ) {
            rol.setEstado( rolDTO.getEstado() );
        }
        rol.setUsuarios( usuarioDTOListToUsuarioList( rolDTO.getUsuarios() ) );

        return rol;
    }

    protected RolDTO rolToRolDTO(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolDTO rolDTO = new RolDTO();

        rolDTO.setId( rol.getId() );
        rolDTO.setNombre( rol.getNombre() );
        rolDTO.setEstado( rol.isEstado() );
        rolDTO.setUsuarios( mapUsuarioToRolDTOs( rol.getUsuarios() ) );

        return rolDTO;
    }

    protected Set<RolDTO> rolSetToRolDTOSet(Set<Rol> set) {
        if ( set == null ) {
            return null;
        }

        Set<RolDTO> set1 = new LinkedHashSet<RolDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Rol rol : set ) {
            set1.add( rolToRolDTO( rol ) );
        }

        return set1;
    }
}
