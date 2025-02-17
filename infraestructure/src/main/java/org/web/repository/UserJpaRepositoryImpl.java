package org.web.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.entities.Usuario;
import org.web.excepciones.NotFoundException;
import org.web.interfaces.UsuarioJpaRepository;
import org.web.jpa.RolEntityJPA;
import org.web.jpa.UsuarioJPA;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserJpaRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final RolEntityMapper rolEntityMapper; // 🔥

    public UserJpaRepositoryImpl(UsuarioJpaRepository usuarioRepository, UsuarioEntityMapper usuarioEntityMapper, RolEntityMapper rolEntityMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.rolEntityMapper = rolEntityMapper;
    }


    @Override
    public Optional<Usuario> findById(long id) {
        Optional<UsuarioJPA> usuarioJPA= this.usuarioRepository.findByIdWithRoles(id); // Corrected and added mapping


        if (usuarioJPA.isPresent()) {


            // ***CRITICAL: Convert to DTO IMMEDIATELY***
            Usuario usuarioDTO = usuarioEntityMapper.toDomain(usuarioJPA.get()); // Use the DTO mapper

            return Optional.of(usuarioDTO); // Return the DTO
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        Optional<UsuarioJPA> usuarioJPA= this.usuarioRepository.findByUsername(username);

        if (usuarioJPA.isPresent()) {
            Usuario usuarioDTO = usuarioEntityMapper.toDomain(usuarioJPA.get()); // Use the DTO mapper
            return Optional.ofNullable(usuarioDTO);
        }
        throw new NotFoundException("Usuario not found");
    }

    @Override
    public List<Usuario> findALL() {
         List<UsuarioJPA> usuarioJPAList = usuarioRepository.findAll();

        // ***CRITICAL: Convert to DTOs for the entire list***

        return usuarioJPAList.stream()
                .map(usuarioEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario save(Usuario usuario) {

        UsuarioJPA usuarioJPA = usuarioEntityMapper.toEntity(usuario);
        Optional<UsuarioJPA> existingUsuarioJPA= Optional.empty();

        if (usuario.getId() == null) { // Insert new user
            // Handle the roles update.

            Set<RolEntityJPA> newRoles = usuario.getRoles().stream()
                    .map(rolEntityMapper::toEntity).collect(Collectors.toSet());

            usuarioJPA.setRoles(newRoles);
            usuarioJPA = usuarioRepository.save(usuarioJPA); // Save the Usuario first

        }

       return  usuarioEntityMapper.toDomain(usuarioJPA);
    }

    @Override
    public Usuario update(Usuario usuario) {
        Optional<UsuarioJPA> existingUsuarioJPA=this.usuarioRepository.findByIdWithRoles(usuario.getId());

        if (existingUsuarioJPA.isPresent()) {
            // Update only the necessary fields (username, email, password, etc.)
            existingUsuarioJPA.get().setUsername(usuario.getUsername());
            existingUsuarioJPA.get().setEmail(usuario.getEmail());
            existingUsuarioJPA.get().setPassword(usuario.getPassword());

            // Handle the roles update.
            Set<RolEntityJPA> newRoles = usuario.getRoles().stream()
                    .map(rolEntityMapper::toEntity)
                    .collect(Collectors.toSet());

            existingUsuarioJPA.get().setRoles(newRoles);
            usuarioRepository.save(existingUsuarioJPA.get());
            return  this.usuarioEntityMapper.toDomain(existingUsuarioJPA.get());

        } else {
            throw new NotFoundException("Usuario not found");
        }



    }

    @Override
    public void deleteById(long id) {
        this.usuarioRepository.deleteById(id); // Corrected ID type
    }
}
