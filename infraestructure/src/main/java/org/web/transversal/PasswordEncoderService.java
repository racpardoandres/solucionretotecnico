package org.web.transversal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.web.repository.PasswordEncodeRepository;

@Component // O @Service si es un servicio
public class PasswordEncoderService implements PasswordEncodeRepository {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encriptar(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean comparar(String passwordPlano, String passwordEncriptado) {
        return passwordEncoder.matches(passwordPlano, passwordEncriptado);
    }
}
