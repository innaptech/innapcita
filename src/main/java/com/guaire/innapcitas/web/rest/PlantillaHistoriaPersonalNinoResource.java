package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaHistoriaPersonalNino;
import com.guaire.innapcitas.repository.PlantillaHistoriaPersonalNinoRepository;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PlantillaHistoriaPersonalNino.
 */
@RestController
@RequestMapping("/api")
public class PlantillaHistoriaPersonalNinoResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaHistoriaPersonalNinoResource.class);

    private static final String ENTITY_NAME = "plantillaHistoriaPersonalNino";

    private final PlantillaHistoriaPersonalNinoRepository plantillaHistoriaPersonalNinoRepository;

    public PlantillaHistoriaPersonalNinoResource(PlantillaHistoriaPersonalNinoRepository plantillaHistoriaPersonalNinoRepository) {
        this.plantillaHistoriaPersonalNinoRepository = plantillaHistoriaPersonalNinoRepository;
    }

    /**
     * POST  /plantilla-historia-personal-ninos : Create a new plantillaHistoriaPersonalNino.
     *
     * @param plantillaHistoriaPersonalNino the plantillaHistoriaPersonalNino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaHistoriaPersonalNino, or with status 400 (Bad Request) if the plantillaHistoriaPersonalNino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-historia-personal-ninos")
    public ResponseEntity<PlantillaHistoriaPersonalNino> createPlantillaHistoriaPersonalNino(@RequestBody PlantillaHistoriaPersonalNino plantillaHistoriaPersonalNino) throws URISyntaxException {
        log.debug("REST request to save PlantillaHistoriaPersonalNino : {}", plantillaHistoriaPersonalNino);
        if (plantillaHistoriaPersonalNino.getId() != null) {
            throw new BadRequestAlertException("A new plantillaHistoriaPersonalNino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaHistoriaPersonalNino result = plantillaHistoriaPersonalNinoRepository.save(plantillaHistoriaPersonalNino);
        return ResponseEntity.created(new URI("/api/plantilla-historia-personal-ninos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-historia-personal-ninos : Updates an existing plantillaHistoriaPersonalNino.
     *
     * @param plantillaHistoriaPersonalNino the plantillaHistoriaPersonalNino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaHistoriaPersonalNino,
     * or with status 400 (Bad Request) if the plantillaHistoriaPersonalNino is not valid,
     * or with status 500 (Internal Server Error) if the plantillaHistoriaPersonalNino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-historia-personal-ninos")
    public ResponseEntity<PlantillaHistoriaPersonalNino> updatePlantillaHistoriaPersonalNino(@RequestBody PlantillaHistoriaPersonalNino plantillaHistoriaPersonalNino) throws URISyntaxException {
        log.debug("REST request to update PlantillaHistoriaPersonalNino : {}", plantillaHistoriaPersonalNino);
        if (plantillaHistoriaPersonalNino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaHistoriaPersonalNino result = plantillaHistoriaPersonalNinoRepository.save(plantillaHistoriaPersonalNino);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaHistoriaPersonalNino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-historia-personal-ninos : get all the plantillaHistoriaPersonalNinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaHistoriaPersonalNinos in body
     */
    @GetMapping("/plantilla-historia-personal-ninos")
    public ResponseEntity<List<PlantillaHistoriaPersonalNino>> getAllPlantillaHistoriaPersonalNinos(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaHistoriaPersonalNinos");
        Page<PlantillaHistoriaPersonalNino> page = plantillaHistoriaPersonalNinoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-historia-personal-ninos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-historia-personal-ninos/:id : get the "id" plantillaHistoriaPersonalNino.
     *
     * @param id the id of the plantillaHistoriaPersonalNino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaHistoriaPersonalNino, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-historia-personal-ninos/{id}")
    public ResponseEntity<PlantillaHistoriaPersonalNino> getPlantillaHistoriaPersonalNino(@PathVariable Long id) {
        log.debug("REST request to get PlantillaHistoriaPersonalNino : {}", id);
        Optional<PlantillaHistoriaPersonalNino> plantillaHistoriaPersonalNino = plantillaHistoriaPersonalNinoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaHistoriaPersonalNino);
    }

    /**
     * DELETE  /plantilla-historia-personal-ninos/:id : delete the "id" plantillaHistoriaPersonalNino.
     *
     * @param id the id of the plantillaHistoriaPersonalNino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-historia-personal-ninos/{id}")
    public ResponseEntity<Void> deletePlantillaHistoriaPersonalNino(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaHistoriaPersonalNino : {}", id);
        plantillaHistoriaPersonalNinoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
