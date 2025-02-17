package org.web.repository;


import org.web.entities.Rol;

import java.util.ArrayList;
import java.util.Optional;

public interface RolRepository {

   Optional<Rol> findById(int id);

    ArrayList<Rol> findALL();

    Rol save(Rol rol);

    void deleteById(int id);


}
