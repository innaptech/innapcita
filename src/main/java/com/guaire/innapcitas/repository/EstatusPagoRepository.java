package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.EstatusPago;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstatusPago entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstatusPagoRepository extends JpaRepository<EstatusPago, Long> {

}
