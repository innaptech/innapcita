package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.Bitacora;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Bitacora entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {

    @Query("select bitacora from Bitacora bitacora where bitacora.user.login = ?#{principal.username}")
    List<Bitacora> findByUserIsCurrentUser();

}
