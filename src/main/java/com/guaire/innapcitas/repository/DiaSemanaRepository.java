package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.DiaSemana;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DiaSemana entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiaSemanaRepository extends JpaRepository<DiaSemana, Long> {

}
