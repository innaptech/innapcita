package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.RangoHoras;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the RangoHoras entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RangoHorasRepository extends JpaRepository<RangoHoras, Long> {

    List<RangoHoras> findByHorarioDisponibilidad_Id(Long id);

}
