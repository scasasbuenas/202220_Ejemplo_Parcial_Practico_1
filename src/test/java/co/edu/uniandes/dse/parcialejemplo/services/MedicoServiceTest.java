package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MedicoService.class)
public class MedicoServiceTest {

    // Inyección para tener acceso a los métodos del servicio MatchService.
    @Autowired
    private MedicoService medicoService;
    
    // Define un entity manager para las pruebas (acceso a métodos para persistir y recuperar datos de la persistencia sin pasar por el servicio).
    @Autowired
    private TestEntityManager entityManager;

    // Facilita la creación de instancias de objetos con datos ficticios.
    private PodamFactory factory = new PodamFactoryImpl();

    // Lista de entidades que ayudarán a crear las pruebas.
    private List<MedicoEntity> medicoList = new ArrayList<>();
    
    /*
     * Configuración inicial de la prueba.
     */
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    /**
     * Limpiar las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity");
    }

    /**
     * Insertar los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medicoEntity);
            medicoList.add(medicoEntity);
        }
    }


    /**
     * Prueba para crear un partido.
     * 
     * @throws EntityNotFoundException, IllegalOperationException
     */
    @Test
    void testCreateMedicos() throws EntityNotFoundException, IllegalOperationException {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        newEntity.setRegistroMedico("RM1745");
        MedicoEntity result = medicoService.createMedicos(newEntity);
        assertNotNull(result);

        MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
    }
    
}
