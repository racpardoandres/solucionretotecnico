package org.web.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.web.jpa.UsuarioJPA;
import org.web.repository.UsuarioRepository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioJPA,Long> {

    @Transactional(readOnly = true)
    @Query("SELECT u FROM UsuarioJPA u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<UsuarioJPA> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM UsuarioJPA u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<UsuarioJPA> findByIdWithRoles(@Param("id") Long id);
}
