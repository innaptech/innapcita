package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.MonedaCambio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonedaCambio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonedaCambioRepository extends JpaRepository<MonedaCambio, Long> {

}
