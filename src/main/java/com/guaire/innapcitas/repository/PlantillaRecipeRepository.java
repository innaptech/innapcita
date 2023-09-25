package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.PlantillaRecipe;
import com.guaire.innapcitas.web.rest.AuditResource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PlantillaRecipe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlantillaRecipeRepository extends JpaRepository<PlantillaRecipe, Long> {

    @Query("select plantilla_recipe from PlantillaRecipe plantilla_recipe where plantilla_recipe.medico.login = ?#{principal.username}")
    List<PlantillaRecipe> findByMedicoIsCurrentUser();

    List<PlantillaRecipe> findByMedico_Id(Long id);

}
