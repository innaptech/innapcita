package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.EstatusCita;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstatusCita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstatusCitaRepository extends JpaRepository<EstatusCita, Long> {

}
