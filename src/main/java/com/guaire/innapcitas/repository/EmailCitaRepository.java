package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.EmailCita;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmailCita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailCitaRepository extends JpaRepository<EmailCita, Long> {

}
