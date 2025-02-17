package org.web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.web.entities.Rol;
import org.web.excepciones.NotFoundException;
import org.web.jpa.RolEntityJPA;
import org.web.mapper.RolEntityMapper;

@Repository
@Transactional
public class RolJpaRepository implements RolRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final RolEntityMapper rolEntityMapper; // 🔥 Inyectamos el mapper de infraestructura


    public RolJpaRepository(EntityManager entityManager, RolEntityMapper rolEntityMapper) {
        this.entityManager= entityManager;
        this.rolEntityMapper = rolEntityMapper;
    }

    @Override
    public Optional<Rol> findById(int id) {
        RolEntityJPA rolEntity = entityManager.find(RolEntityJPA.class, id);
        return Optional.ofNullable(rolEntityMapper.toDomain(rolEntity));
    }

    @Override
    public ArrayList<Rol> findALL() {


        List<RolEntityJPA> entities = entityManager.createQuery("SELECT r FROM RolEntityJPA  r", RolEntityJPA.class)
                .getResultList();
        return (ArrayList<Rol>) rolEntityMapper.toDomainList(entities);

    }

    @Transactional
    @Override
    public Rol save(Rol rol) {
        RolEntityJPA rolEntity = rolEntityMapper.toEntity(rol); // 🔥 Convertimos a entidad JPA
        if (rolEntity.getId() != 0) {
            Optional<Rol> existingRol = this.findById(rolEntity.getId());

            if (existingRol.isEmpty()) {
                throw new NotFoundException("RolEntityJPA con ID " + rolEntity.getId() + " no encontrado.");
            }

            rolEntity = entityManager.merge(rolEntity); // 🔥 Actualizamos si ya existe
        } else {
            entityManager.persist(rolEntity); // 🔥 Insertamos si es nuevo
        }
        return rolEntityMapper.toDomain(rolEntity); // 🔥 Devolvemos la entidad convertida a dominio
    }



        @Override
    public void deleteById(int id) {
        RolEntityJPA rolEntity = entityManager.find(RolEntityJPA.class, id);
        if (rolEntity != null) {
            entityManager.remove(rolEntity);
        }
        else{
            throw new NotFoundException("RolEntityJPA con ID " + id + " no encontrado.");

        }
    }
}
