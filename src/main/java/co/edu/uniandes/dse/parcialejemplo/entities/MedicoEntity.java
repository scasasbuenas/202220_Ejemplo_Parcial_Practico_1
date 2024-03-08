package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicoEntity extends BaseEntity{

    // No hay necesidad de agregar un atributo id, ya que la BaseEntity ya lo tiene.

    private String nombre;
    private String apellido;
    private String registroMedico;

    @PodamExclude
    @ManyToMany
    private List<EspecialidadEntity> especialidades = new ArrayList<>();

    
}
