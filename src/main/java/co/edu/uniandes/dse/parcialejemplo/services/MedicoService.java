package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @SuppressWarnings("null")
    @Transactional
    public MedicoEntity createMedicos(MedicoEntity medicoEntity) throws EntityNotFoundException, IllegalOperationException {
        log.info("Inicia proceso de creación del medico");

        if ((medicoEntity.getId() != null) && !medicoRepository.findById(medicoEntity.getId()).isEmpty()) {
            throw new IllegalOperationException("Medico already exists");
        }

        // Si las validaciones son exitosas:
        log.info("Termina el proceso de creación del medico");
        return medicoRepository.save(medicoEntity);
    }
}
