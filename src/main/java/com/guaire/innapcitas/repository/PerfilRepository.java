package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.Perfil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Perfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("select perfil from Perfil perfil where perfil.user.login = ?#{principal.username}")
    List<Perfil> findByUserIsCurrentUser();

    Perfil findByUser_Id(Long id);
}
