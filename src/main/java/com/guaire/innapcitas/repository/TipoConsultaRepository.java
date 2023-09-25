package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.TipoConsulta;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoConsulta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Long> {

}
