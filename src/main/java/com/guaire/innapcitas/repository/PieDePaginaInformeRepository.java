package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PieDePaginaInforme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PieDePaginaInforme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PieDePaginaInformeRepository extends JpaRepository<PieDePaginaInforme, Long> {

}
