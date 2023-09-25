package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.TipoPago;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoPago entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {

}
