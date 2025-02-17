package org.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.web.mapper.RolEntityMapper;
import org.web.mapper.UsuarioEntityMapper;

@Configuration
public class AnotherConfig {
    @Bean(name = "anotherBean") // Explicit name

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RolEntityMapper rolEntityMapper() {
        return RolEntityMapper.INSTANCE; // 💡 Si usas MapStruct, se genera automáticamente
    }

    @Bean
    public UsuarioEntityMapper usuarioEntityMapper() {
        return UsuarioEntityMapper.INSTANCE; // 💡 Si usas MapStruct, se genera automáticamente
    }


 }