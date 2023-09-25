package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PlantillaHistoriaMedicaNino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlantillaHistoriaMedicaNino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaHistoriaMedicaNinoRepository extends JpaRepository<PlantillaHistoriaMedicaNino, Long> {

}
