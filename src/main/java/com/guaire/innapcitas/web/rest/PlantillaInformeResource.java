package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaInforme;
import com.guaire.innapcitas.repository.PlantillaInformeRepository;
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
 * REST controller for managing PlantillaInforme.
 */
@RestController
@RequestMapping("/api")
public class PlantillaInformeResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaInformeResource.class);

    private static final String ENTITY_NAME = "plantillaInforme";

    private final PlantillaInformeRepository plantillaInformeRepository;

    public PlantillaInformeResource(PlantillaInformeRepository plantillaInformeRepository) {
        this.plantillaInformeRepository = plantillaInformeRepository;
    }

    /**
     * POST  /plantilla-informes : Create a new plantillaInforme.
     *
     * @param plantillaInforme the plantillaInforme to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaInforme, or with status 400 (Bad Request) if the plantillaInforme has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-informes")
    public ResponseEntity<PlantillaInforme> createPlantillaInforme(@Valid @RequestBody PlantillaInforme plantillaInforme) throws URISyntaxException {
        log.debug("REST request to save PlantillaInforme : {}", plantillaInforme);
        if (plantillaInforme.getId() != null) {
            throw new BadRequestAlertException("A new plantillaInforme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaInforme result = plantillaInformeRepository.save(plantillaInforme);
        return ResponseEntity.created(new URI("/api/plantilla-informes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-informes : Updates an existing plantillaInforme.
     *
     * @param plantillaInforme the plantillaInforme to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaInforme,
     * or with status 400 (Bad Request) if the plantillaInforme is not valid,
     * or with status 500 (Internal Server Error) if the plantillaInforme couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-informes")
    public ResponseEntity<PlantillaInforme> updatePlantillaInforme(@Valid @RequestBody PlantillaInforme plantillaInforme) throws URISyntaxException {
        log.debug("REST request to update PlantillaInforme : {}", plantillaInforme);
        if (plantillaInforme.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaInforme result = plantillaInformeRepository.save(plantillaInforme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaInforme.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-informes : get all the plantillaInformes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaInformes in body
     */
    @GetMapping("/plantilla-informes")
    public ResponseEntity<List<PlantillaInforme>> getAllPlantillaInformes(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaInformes");
        Page<PlantillaInforme> page = plantillaInformeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-informes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-informes/:id : get the "id" plantillaInforme.
     *
     * @param id the id of the plantillaInforme to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaInforme, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-informes/{id}")
    public ResponseEntity<PlantillaInforme> getPlantillaInforme(@PathVariable Long id) {
        log.debug("REST request to get PlantillaInforme : {}", id);
        Optional<PlantillaInforme> plantillaInforme = plantillaInformeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaInforme);
    }

    /**
     * DELETE  /plantilla-informes/:id : delete the "id" plantillaInforme.
     *
     * @param id the id of the plantillaInforme to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-informes/{id}")
    public ResponseEntity<Void> deletePlantillaInforme(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaInforme : {}", id);
        plantillaInformeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
