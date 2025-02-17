package org.web.usecase.usuario;

import org.springframework.stereotype.Service;
import org.web.inputport.usuario.DeleteUsuarioUseCase;
import org.web.repository.UsuarioRepository;

@Service
public class EliminarUsuarioUseCaseImpl implements DeleteUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public EliminarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public void excute(int id) {
        this.usuarioRepository.deleteById(id);
    }
}
