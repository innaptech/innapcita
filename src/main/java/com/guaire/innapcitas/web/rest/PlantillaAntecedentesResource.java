package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaAntecedentes;
import com.guaire.innapcitas.repository.PlantillaAntecedentesRepository;
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
 * REST controller for managing PlantillaAntecedentes.
 */
@RestController
@RequestMapping("/api")
public class PlantillaAntecedentesResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaAntecedentesResource.class);

    private static final String ENTITY_NAME = "plantillaAntecedentes";

    private final PlantillaAntecedentesRepository plantillaAntecedentesRepository;

    public PlantillaAntecedentesResource(PlantillaAntecedentesRepository plantillaAntecedentesRepository) {
        this.plantillaAntecedentesRepository = plantillaAntecedentesRepository;
    }

    /**
     * POST  /plantilla-antecedentes : Create a new plantillaAntecedentes.
     *
     * @param plantillaAntecedentes the plantillaAntecedentes to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaAntecedentes, or with status 400 (Bad Request) if the plantillaAntecedentes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-antecedentes")
    public ResponseEntity<PlantillaAntecedentes> createPlantillaAntecedentes(@Valid @RequestBody PlantillaAntecedentes plantillaAntecedentes) throws URISyntaxException {
        log.debug("REST request to save PlantillaAntecedentes : {}", plantillaAntecedentes);
        if (plantillaAntecedentes.getId() != null) {
            throw new BadRequestAlertException("A new plantillaAntecedentes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaAntecedentes result = plantillaAntecedentesRepository.save(plantillaAntecedentes);
        return ResponseEntity.created(new URI("/api/plantilla-antecedentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-antecedentes : Updates an existing plantillaAntecedentes.
     *
     * @param plantillaAntecedentes the plantillaAntecedentes to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaAntecedentes,
     * or with status 400 (Bad Request) if the plantillaAntecedentes is not valid,
     * or with status 500 (Internal Server Error) if the plantillaAntecedentes couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-antecedentes")
    public ResponseEntity<PlantillaAntecedentes> updatePlantillaAntecedentes(@Valid @RequestBody PlantillaAntecedentes plantillaAntecedentes) throws URISyntaxException {
        log.debug("REST request to update PlantillaAntecedentes : {}", plantillaAntecedentes);
        if (plantillaAntecedentes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaAntecedentes result = plantillaAntecedentesRepository.save(plantillaAntecedentes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaAntecedentes.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-antecedentes : get all the plantillaAntecedentes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaAntecedentes in body
     */
    @GetMapping("/plantilla-antecedentes")
    public ResponseEntity<List<PlantillaAntecedentes>> getAllPlantillaAntecedentes(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaAntecedentes");
        Page<PlantillaAntecedentes> page = plantillaAntecedentesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-antecedentes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-antecedentes/:id : get the "id" plantillaAntecedentes.
     *
     * @param id the id of the plantillaAntecedentes to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaAntecedentes, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-antecedentes/{id}")
    public ResponseEntity<PlantillaAntecedentes> getPlantillaAntecedentes(@PathVariable Long id) {
        log.debug("REST request to get PlantillaAntecedentes : {}", id);
        Optional<PlantillaAntecedentes> plantillaAntecedentes = plantillaAntecedentesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaAntecedentes);
    }

    /**
     * DELETE  /plantilla-antecedentes/:id : delete the "id" plantillaAntecedentes.
     *
     * @param id the id of the plantillaAntecedentes to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-antecedentes/{id}")
    public ResponseEntity<Void> deletePlantillaAntecedentes(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaAntecedentes : {}", id);
        plantillaAntecedentesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
