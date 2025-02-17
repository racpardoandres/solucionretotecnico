package org.web.test.rol;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.web.AnotherConfig;
import org.web.Main;
import org.web.entities.Rol;
import org.web.jpa.RolEntityJPA;
import org.web.mapper.RolEntityMapper;
import org.web.repository.RolJpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
@ContextConfiguration(classes = Main.class)
@Import(AnotherConfig.class)
public class RolJpaFindALLTest {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RolEntityMapper rolEntityMapper;

    @Autowired
    private RolJpaRepository rolJpaRepository;

    @Test
    void testFindAll() {
        // 1 Almacenar (usando the real EntityManager)
        // 1. Almacenar datos database (usando persist and flush)
        RolEntityJPA rolEntity1 = new RolEntityJPA();
        rolEntity1.setNombre("Role1");
        rolEntity1.setEstado(true);
        entityManager.persist(rolEntity1); // Persist the entity
        entityManager.flush(); // Flush to the database

        RolEntityJPA rolEntity2 = new RolEntityJPA();
        rolEntity2.setNombre("Role2");
        rolEntity2.setEstado(false);
        entityManager.persist(rolEntity2);
        entityManager.flush();

        // 2. Call the repository method
        List<Rol> roles = rolJpaRepository.findALL();

        // 3. Assertions (same as before)
        assertNotNull(roles);
        assertEquals(2, roles.size());

        assertEquals("Role1", roles.get(0).getNombre());
        assertEquals(true, roles.get(0).isEstado());
        assertEquals("Role2", roles.get(1).getNombre());
        assertEquals(false, roles.get(1).isEstado());
    }
}
