package org.web.config.bean;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;
import org.web.repository.RolJpaRepository;
import org.web.repository.RolRepository;

@Configuration
public class BeanConfiguration {

    @Bean
    public RolEntityMapper rolEntityMapper() {
        return RolEntityMapper.INSTANCE; // 💡 Si usas MapStruct, se genera automáticamente
    }

    @Bean
    public UsuarioEntityMapper usuarioEntityMapper() {
        return UsuarioEntityMapper.INSTANCE; // 💡 Si usas MapStruct, se genera automáticamente
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public RolRepository rolRepository(RolEntityMapper rolEntityMapper) {
        return new RolJpaRepository(entityManager, rolEntityMapper);
    }



}
