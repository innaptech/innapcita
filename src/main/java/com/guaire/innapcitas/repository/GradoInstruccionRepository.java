package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.GradoInstruccion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GradoInstruccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GradoInstruccionRepository extends JpaRepository<GradoInstruccion, Long> {

}
