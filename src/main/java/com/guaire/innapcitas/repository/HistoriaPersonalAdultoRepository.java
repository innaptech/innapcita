package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.HistoriaPersonalAdulto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data  repository for the HistoriaPersonalAdulto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoriaPersonalAdultoRepository extends JpaRepository<HistoriaPersonalAdulto, Long> {

    @Query("select historia_personal_adulto from HistoriaPersonalAdulto historia_personal_adulto where historia_personal_adulto.paciente.login = ?#{principal.username}")
    List<HistoriaPersonalAdulto> findByPacienteIsCurrentUser();

    Set<HistoriaPersonalAdulto> findByPaciente_Id(Long id);

    List<HistoriaPersonalAdulto> getByPaciente_Id(Long id);
}
