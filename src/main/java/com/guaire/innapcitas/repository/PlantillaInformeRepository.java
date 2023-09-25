package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.Especialidad;
import com.guaire.innapcitas.domain.PlantillaInforme;
import com.guaire.innapcitas.web.rest.AuditResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the PlantillaInforme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaInformeRepository extends JpaRepository<PlantillaInforme, Long> {

    List<PlantillaInforme> findByEspecialidad_Id(Long especialidad);
}
