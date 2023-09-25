package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PlantillaHistoriaPersonalAdulto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantillaHistoriaPersonalAdulto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaHistoriaPersonalAdultoRepository extends JpaRepository<PlantillaHistoriaPersonalAdulto, Long> {

}
