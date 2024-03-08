package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @SuppressWarnings("null")
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación de la especialidad");

        if ((especialidadEntity.getId() != null) && !especialidadRepository.findById(especialidadEntity.getId()).isEmpty()) {
            throw new IllegalOperationException("Especialidad already exists");
        }

        // Si las validaciones son exitosas:
        log.info("Termina el proceso de creación del medico");
        return especialidadRepository.save(especialidadEntity);
    }
}
