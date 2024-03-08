package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class EspecialidadServiceTest {

    @Autowired
    private EspecialidadService especialidadService;
    
    // Define un entity manager para las pruebas (acceso a métodos para persistir y recuperar datos de la persistencia sin pasar por el servicio).
    @Autowired
    private TestEntityManager entityManager;

    // Facilita la creación de instancias de objetos con datos ficticios.
    private PodamFactory factory = new PodamFactoryImpl();

    // Lista de entidades que ayudarán a crear las pruebas.
    private List<EspecialidadEntity> especialidadList = new ArrayList<>();
    
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
            EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(especialidadEntity);
            especialidadList.add(especialidadEntity);
        }
    }


    /**
     * Prueba para crear un medico.
     * 
     * @throws EntityNotFoundException, IllegalOperationException
     */
    @Test
    void testCreateEspecialidad() throws EntityNotFoundException, IllegalOperationException {
        EspecialidadEntity newEntity = factory.manufacturePojo(EspecialidadEntity.class);
        //newEntity.setDescripcion("1234567890");
        EspecialidadEntity result = especialidadService.createEspecialidad(newEntity);
        assertNotNull(result);

        EspecialidadEntity entity = entityManager.find(EspecialidadEntity.class, result.getId());
        assertEquals(newEntity.getId(), entity.getId());
    }
    
}
