package org.web.test.rol;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.web.jpa.RolEntityJPA;
import org.web.mapper.RolEntityMapper;
import org.web.repository.RolJpaRepository;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RolJpaDeleteTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @InjectMocks
    private RolJpaRepository rolJpaRepository;


    @Test
    public void testDeleteById_ExistingRol() {
        int id = 1;
        RolEntityJPA rolEntityJPA = new RolEntityJPA();

        when(entityManager.find(RolEntityJPA.class, id)).thenReturn(rolEntityJPA);

        rolJpaRepository.deleteById(id);

        verify(entityManager).remove(rolEntityJPA);
    }

}
