package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaHistoriaMedicaNino;
import com.guaire.innapcitas.repository.PlantillaHistoriaMedicaNinoRepository;
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
 * REST controller for managing PlantillaHistoriaMedicaNino.
 */
@RestController
@RequestMapping("/api")
public class PlantillaHistoriaMedicaNinoResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaHistoriaMedicaNinoResource.class);

    private static final String ENTITY_NAME = "plantillaHistoriaMedicaNino";

    private final PlantillaHistoriaMedicaNinoRepository plantillaHistoriaMedicaNinoRepository;

    public PlantillaHistoriaMedicaNinoResource(PlantillaHistoriaMedicaNinoRepository plantillaHistoriaMedicaNinoRepository) {
        this.plantillaHistoriaMedicaNinoRepository = plantillaHistoriaMedicaNinoRepository;
    }

    /**
     * POST  /plantilla-historia-medica-ninos : Create a new plantillaHistoriaMedicaNino.
     *
     * @param plantillaHistoriaMedicaNino the plantillaHistoriaMedicaNino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaHistoriaMedicaNino, or with status 400 (Bad Request) if the plantillaHistoriaMedicaNino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-historia-medica-ninos")
    public ResponseEntity<PlantillaHistoriaMedicaNino> createPlantillaHistoriaMedicaNino(@RequestBody PlantillaHistoriaMedicaNino plantillaHistoriaMedicaNino) throws URISyntaxException {
        log.debug("REST request to save PlantillaHistoriaMedicaNino : {}", plantillaHistoriaMedicaNino);
        if (plantillaHistoriaMedicaNino.getId() != null) {
            throw new BadRequestAlertException("A new plantillaHistoriaMedicaNino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaHistoriaMedicaNino result = plantillaHistoriaMedicaNinoRepository.save(plantillaHistoriaMedicaNino);
        return ResponseEntity.created(new URI("/api/plantilla-historia-medica-ninos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-historia-medica-ninos : Updates an existing plantillaHistoriaMedicaNino.
     *
     * @param plantillaHistoriaMedicaNino the plantillaHistoriaMedicaNino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaHistoriaMedicaNino,
     * or with status 400 (Bad Request) if the plantillaHistoriaMedicaNino is not valid,
     * or with status 500 (Internal Server Error) if the plantillaHistoriaMedicaNino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-historia-medica-ninos")
    public ResponseEntity<PlantillaHistoriaMedicaNino> updatePlantillaHistoriaMedicaNino(@RequestBody PlantillaHistoriaMedicaNino plantillaHistoriaMedicaNino) throws URISyntaxException {
        log.debug("REST request to update PlantillaHistoriaMedicaNino : {}", plantillaHistoriaMedicaNino);
        if (plantillaHistoriaMedicaNino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaHistoriaMedicaNino result = plantillaHistoriaMedicaNinoRepository.save(plantillaHistoriaMedicaNino);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaHistoriaMedicaNino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-historia-medica-ninos : get all the plantillaHistoriaMedicaNinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaHistoriaMedicaNinos in body
     */
    @GetMapping("/plantilla-historia-medica-ninos")
    public ResponseEntity<List<PlantillaHistoriaMedicaNino>> getAllPlantillaHistoriaMedicaNinos(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaHistoriaMedicaNinos");
        Page<PlantillaHistoriaMedicaNino> page = plantillaHistoriaMedicaNinoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-historia-medica-ninos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-historia-medica-ninos/:id : get the "id" plantillaHistoriaMedicaNino.
     *
     * @param id the id of the plantillaHistoriaMedicaNino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaHistoriaMedicaNino, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-historia-medica-ninos/{id}")
    public ResponseEntity<PlantillaHistoriaMedicaNino> getPlantillaHistoriaMedicaNino(@PathVariable Long id) {
        log.debug("REST request to get PlantillaHistoriaMedicaNino : {}", id);
        Optional<PlantillaHistoriaMedicaNino> plantillaHistoriaMedicaNino = plantillaHistoriaMedicaNinoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaHistoriaMedicaNino);
    }

    /**
     * DELETE  /plantilla-historia-medica-ninos/:id : delete the "id" plantillaHistoriaMedicaNino.
     *
     * @param id the id of the plantillaHistoriaMedicaNino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-historia-medica-ninos/{id}")
    public ResponseEntity<Void> deletePlantillaHistoriaMedicaNino(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaHistoriaMedicaNino : {}", id);
        plantillaHistoriaMedicaNinoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
