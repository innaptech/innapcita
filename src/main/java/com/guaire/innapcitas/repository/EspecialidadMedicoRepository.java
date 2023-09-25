package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.EspecialidadMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the EspecialidadMedico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EspecialidadMedicoRepository extends JpaRepository<EspecialidadMedico, Long> {

    @Query("select especialidad_medico from EspecialidadMedico especialidad_medico where especialidad_medico.medico.login = ?#{principal.username}")
    List<EspecialidadMedico> findByMedicoIsCurrentUser();

    @Query("select especialidad_medico from EspecialidadMedico especialidad_medico where especialidad_medico.medico.login = ?#{principal.username}")
    Page<EspecialidadMedico> findByMedicoIsCurrentUser(Pageable pageable);

    Page<EspecialidadMedico> findAllByDescripcionStartsWithIgnoreCaseOrMedico_LastNameStartsWithIgnoreCaseOrMedico_FirstNameStartsWithIgnoreCaseOrEspecialidad_EspecialidadStartsWithIgnoreCase(String query, String query1, String query2, String query3, Pageable pageable);

    Page<EspecialidadMedico> findAllByDescripcionStartsWithIgnoreCaseOrMedico_LastNameStartsWithIgnoreCaseOrMedico_FirstNameStartsWithIgnoreCaseOrEspecialidad_EspecialidadContainsIgnoreCase(String query, String query1, String query2, String query3, Pageable pageable);
}
