package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.EvolucionPaciente;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the EvolucionPaciente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvolucionPacienteRepository extends JpaRepository<EvolucionPaciente, Long> {

    EvolucionPaciente findByCita_Id(Long id);

    List<EvolucionPaciente> findByCita_Paciente_Id(Long id);
}
