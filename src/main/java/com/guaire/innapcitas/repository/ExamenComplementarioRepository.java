package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.ExamenComplementario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ExamenComplementario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamenComplementarioRepository extends JpaRepository<ExamenComplementario, Long> {

    List<ExamenComplementario> findByEvolucionPaciente_Id(Long id);
}
