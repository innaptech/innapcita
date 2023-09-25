package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.HistoriaMedicaNino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data  repository for the HistoriaMedicaNino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriaMedicaNinoRepository extends JpaRepository<HistoriaMedicaNino, Long> {

    @Query("select historia_medica_nino from HistoriaMedicaNino historia_medica_nino where historia_medica_nino.paciente.login = ?#{principal.username}")
    List<HistoriaMedicaNino> findByPacienteIsCurrentUser();

    Set<HistoriaMedicaNino> findByPaciente_Id(Long id);
}
