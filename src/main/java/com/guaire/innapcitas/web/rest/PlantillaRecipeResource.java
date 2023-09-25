package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaRecipe;
import com.guaire.innapcitas.repository.PlantillaRecipeRepository;
import com.guaire.innapcitas.web.rest.errors.BadRequestAlertException;
import com.guaire.innapcitas.web.rest.util.HeaderUtil;
import com.guaire.innapcitas.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PlantillaRecipe.
 */
@RestController
@RequestMapping("/api")
public class PlantillaRecipeResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaRecipeResource.class);

    private static final String ENTITY_NAME = "plantillaRecipe";

    private final PlantillaRecipeRepository plantillaRecipeRepository;

    public PlantillaRecipeResource(PlantillaRecipeRepository plantillaRecipeRepository) {
        this.plantillaRecipeRepository = plantillaRecipeRepository;
    }

    /**
     * POST  /plantilla-recipes : Create a new plantillaRecipe.
     *
     * @param plantillaRecipe the plantillaRecipe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaRecipe, or with status 400 (Bad Request) if the plantillaRecipe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-recipes")
    public ResponseEntity<PlantillaRecipe> createPlantillaRecipe(@Valid @RequestBody PlantillaRecipe plantillaRecipe) throws URISyntaxException {
        log.debug("REST request to save PlantillaRecipe : {}", plantillaRecipe);
        if (plantillaRecipe.getId() != null) {
            throw new BadRequestAlertException("A new plantillaRecipe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaRecipe result = plantillaRecipeRepository.save(plantillaRecipe);
        return ResponseEntity.created(new URI("/api/plantilla-recipes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-recipes : Updates an existing plantillaRecipe.
     *
     * @param plantillaRecipe the plantillaRecipe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaRecipe,
     * or with status 400 (Bad Request) if the plantillaRecipe is not valid,
     * or with status 500 (Internal Server Error) if the plantillaRecipe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-recipes")
    public ResponseEntity<PlantillaRecipe> updatePlantillaRecipe(@Valid @RequestBody PlantillaRecipe plantillaRecipe) throws URISyntaxException {
        log.debug("REST request to update PlantillaRecipe : {}", plantillaRecipe);
        if (plantillaRecipe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaRecipe result = plantillaRecipeRepository.save(plantillaRecipe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaRecipe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-recipes : get all the plantillaRecipes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaRecipes in body
     */
    @GetMapping("/plantilla-recipes")
    public ResponseEntity<List<PlantillaRecipe>> getAllPlantillaRecipes(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaRecipes");
        Page<PlantillaRecipe> page = plantillaRecipeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-recipes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-recipes/:id : get the "id" plantillaRecipe.
     *
     * @param id the id of the plantillaRecipe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaRecipe, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-recipes/{id}")
    public ResponseEntity<PlantillaRecipe> getPlantillaRecipe(@PathVariable Long id) {
        log.debug("REST request to get PlantillaRecipe : {}", id);
        Optional<PlantillaRecipe> plantillaRecipe = plantillaRecipeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaRecipe);
    }

    /**
     * DELETE  /plantilla-recipes/:id : delete the "id" plantillaRecipe.
     *
     * @param id the id of the plantillaRecipe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-recipes/{id}")
    public ResponseEntity<Void> deletePlantillaRecipe(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaRecipe : {}", id);
        plantillaRecipeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
