package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.HistoriaMedicaAdulto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data  repository for the HistoriaMedicaAdulto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriaMedicaAdultoRepository extends JpaRepository<HistoriaMedicaAdulto, Long> {

    @Query("select historia_medica_adulto from HistoriaMedicaAdulto historia_medica_adulto where historia_medica_adulto.paciente.login = ?#{principal.username}")
    List<HistoriaMedicaAdulto> findByPacienteIsCurrentUser();

    Set<HistoriaMedicaAdulto> findByPaciente_Id(Long id);
}
