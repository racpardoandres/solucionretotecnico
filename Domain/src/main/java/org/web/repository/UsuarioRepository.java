package org.web.repository;


import org.springframework.data.repository.query.Param;
import org.web.entities.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(long id);

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findALL();

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    void deleteById(long id);


}
