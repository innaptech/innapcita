package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.CabeceraInforme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CabeceraInforme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CabeceraInformeRepository extends JpaRepository<CabeceraInforme, Long> {

}
