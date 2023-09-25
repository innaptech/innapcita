package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PlantillaHistoriaPersonalNino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantillaHistoriaPersonalNino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaHistoriaPersonalNinoRepository extends JpaRepository<PlantillaHistoriaPersonalNino, Long> {

}
