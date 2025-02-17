package org.web.usecase.rol;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.web.inputport.rol.DeleteRolUseCase;
import org.web.repository.RolRepository;

@Component
public class EliminarRolUseCaseImpl implements DeleteRolUseCase {

    private final RolRepository rolRepository;

    public EliminarRolUseCaseImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }


    @Override
    public void excute(int id) {
         this.rolRepository.deleteById(id);
    }
}
