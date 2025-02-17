package org.web.test.rol;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web.entities.Rol;
import org.web.jpa.RolEntityJPA;
import org.web.mapper.RolEntityMapper;
import org.web.repository.RolJpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class RolJpaFindByIdTest {


    @Mock
    private EntityManager entityManager;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private RolJpaRepository rolJpaRepository;

    @Test
    public void testFindById() {
        int id = 1;
        RolEntityJPA rolEntityJPA = new RolEntityJPA();
        Rol rol = new Rol();

        when(entityManager.find(RolEntityJPA.class, id)).thenReturn(rolEntityJPA);
        when(rolEntityMapper.toDomain(rolEntityJPA)).thenReturn(rol);

        Optional<Rol> result = rolJpaRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(rol, result.get());
    }



}
