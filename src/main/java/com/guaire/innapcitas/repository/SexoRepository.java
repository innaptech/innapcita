package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.Sexo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sexo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {

}
