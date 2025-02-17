package org.web.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web.dto.rol.ActualizarRolDTO;
import org.web.dto.rol.CrearRolDTO;
import org.web.dto.rol.RolDTO;
import org.web.entities.Rol;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T22:56:07-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RolMapperImpl implements RolMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Rol toEntity(CrearRolDTO crearRolDTO) {
        if ( crearRolDTO == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setNombre( crearRolDTO.getNombre() );
        if ( crearRolDTO.getEstado() != null ) {
            rol.setEstado( crearRolDTO.getEstado() );
        }

        return rol;
    }

    @Override
    public Rol updateRolFromDTO(ActualizarRolDTO actualizarRolDTO) {
        if ( actualizarRolDTO == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setId( actualizarRolDTO.getId() );
        rol.setNombre( actualizarRolDTO.getNombre() );
        if ( actualizarRolDTO.getEstado() != null ) {
            rol.setEstado( actualizarRolDTO.getEstado() );
        }

        return rol;
    }

    @Override
    public RolDTO toDTO(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolDTO rolDTO = new RolDTO();

        rolDTO.setId( rol.getId() );
        rolDTO.setNombre( rol.getNombre() );
        rolDTO.setEstado( rol.isEstado() );
        rolDTO.setUsuarios( usuarioMapper.mapUsuarioToRolDTOs( rol.getUsuarios() ) );

        return rolDTO;
    }

    @Override
    public void updateRolFromDTO(ActualizarRolDTO actualizarRolDTO, Rol rol) {
        if ( actualizarRolDTO == null ) {
            return;
        }

        rol.setId( actualizarRolDTO.getId() );
        rol.setNombre( actualizarRolDTO.getNombre() );
        if ( actualizarRolDTO.getEstado() != null ) {
            rol.setEstado( actualizarRolDTO.getEstado() );
        }
    }

    @Override
    public List<RolDTO> mapRolesToRolDTOs(List<Rol> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RolDTO> list = new ArrayList<RolDTO>( roles.size() );
        for ( Rol rol : roles ) {
            list.add( toDTO( rol ) );
        }

        return list;
    }
}
