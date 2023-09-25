package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.HorarioDisponibilidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HorarioDisponibilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioDisponibilidadRepository extends JpaRepository<HorarioDisponibilidad, Long> {

    Page<HorarioDisponibilidad> findByEspecialidadMedico_Medico_LoginLikeIgnoreCase(String medico, Pageable pageable);

}
