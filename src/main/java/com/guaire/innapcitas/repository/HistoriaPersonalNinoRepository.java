package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.HistoriaPersonalNino;
import com.guaire.innapcitas.web.rest.AuditResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data  repository for the HistoriaPersonalNino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriaPersonalNinoRepository extends JpaRepository<HistoriaPersonalNino, Long> {

    @Query("select historia_personal_nino from HistoriaPersonalNino historia_personal_nino where historia_personal_nino.paciente.login = ?#{principal.username}")
    List<HistoriaPersonalNino> findByPacienteIsCurrentUser();

    Set<HistoriaPersonalNino> findByPaciente_Id(Long id);

    List<HistoriaPersonalNino> getByPaciente_Id(Long id);
}
