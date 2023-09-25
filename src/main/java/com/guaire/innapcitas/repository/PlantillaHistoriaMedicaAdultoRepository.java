package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PlantillaHistoriaMedicaAdulto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantillaHistoriaMedicaAdulto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaHistoriaMedicaAdultoRepository extends JpaRepository<PlantillaHistoriaMedicaAdulto, Long> {

}
