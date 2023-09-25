package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaHistoriaPersonalAdulto;
import com.guaire.innapcitas.repository.PlantillaHistoriaPersonalAdultoRepository;
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
 * REST controller for managing PlantillaHistoriaPersonalAdulto.
 */
@RestController
@RequestMapping("/api")
public class PlantillaHistoriaPersonalAdultoResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaHistoriaPersonalAdultoResource.class);

    private static final String ENTITY_NAME = "plantillaHistoriaPersonalAdulto";

    private final PlantillaHistoriaPersonalAdultoRepository plantillaHistoriaPersonalAdultoRepository;

    public PlantillaHistoriaPersonalAdultoResource(PlantillaHistoriaPersonalAdultoRepository plantillaHistoriaPersonalAdultoRepository) {
        this.plantillaHistoriaPersonalAdultoRepository = plantillaHistoriaPersonalAdultoRepository;
    }

    /**
     * POST  /plantilla-historia-personal-adultos : Create a new plantillaHistoriaPersonalAdulto.
     *
     * @param plantillaHistoriaPersonalAdulto the plantillaHistoriaPersonalAdulto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaHistoriaPersonalAdulto, or with status 400 (Bad Request) if the plantillaHistoriaPersonalAdulto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-historia-personal-adultos")
    public ResponseEntity<PlantillaHistoriaPersonalAdulto> createPlantillaHistoriaPersonalAdulto(@RequestBody PlantillaHistoriaPersonalAdulto plantillaHistoriaPersonalAdulto) throws URISyntaxException {
        log.debug("REST request to save PlantillaHistoriaPersonalAdulto : {}", plantillaHistoriaPersonalAdulto);
        if (plantillaHistoriaPersonalAdulto.getId() != null) {
            throw new BadRequestAlertException("A new plantillaHistoriaPersonalAdulto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaHistoriaPersonalAdulto result = plantillaHistoriaPersonalAdultoRepository.save(plantillaHistoriaPersonalAdulto);
        return ResponseEntity.created(new URI("/api/plantilla-historia-personal-adultos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-historia-personal-adultos : Updates an existing plantillaHistoriaPersonalAdulto.
     *
     * @param plantillaHistoriaPersonalAdulto the plantillaHistoriaPersonalAdulto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaHistoriaPersonalAdulto,
     * or with status 400 (Bad Request) if the plantillaHistoriaPersonalAdulto is not valid,
     * or with status 500 (Internal Server Error) if the plantillaHistoriaPersonalAdulto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-historia-personal-adultos")
    public ResponseEntity<PlantillaHistoriaPersonalAdulto> updatePlantillaHistoriaPersonalAdulto(@RequestBody PlantillaHistoriaPersonalAdulto plantillaHistoriaPersonalAdulto) throws URISyntaxException {
        log.debug("REST request to update PlantillaHistoriaPersonalAdulto : {}", plantillaHistoriaPersonalAdulto);
        if (plantillaHistoriaPersonalAdulto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaHistoriaPersonalAdulto result = plantillaHistoriaPersonalAdultoRepository.save(plantillaHistoriaPersonalAdulto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaHistoriaPersonalAdulto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-historia-personal-adultos : get all the plantillaHistoriaPersonalAdultos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaHistoriaPersonalAdultos in body
     */
    @GetMapping("/plantilla-historia-personal-adultos")
    public ResponseEntity<List<PlantillaHistoriaPersonalAdulto>> getAllPlantillaHistoriaPersonalAdultos(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaHistoriaPersonalAdultos");
        Page<PlantillaHistoriaPersonalAdulto> page = plantillaHistoriaPersonalAdultoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-historia-personal-adultos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-historia-personal-adultos/:id : get the "id" plantillaHistoriaPersonalAdulto.
     *
     * @param id the id of the plantillaHistoriaPersonalAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaHistoriaPersonalAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-historia-personal-adultos/{id}")
    public ResponseEntity<PlantillaHistoriaPersonalAdulto> getPlantillaHistoriaPersonalAdulto(@PathVariable Long id) {
        log.debug("REST request to get PlantillaHistoriaPersonalAdulto : {}", id);
        Optional<PlantillaHistoriaPersonalAdulto> plantillaHistoriaPersonalAdulto = plantillaHistoriaPersonalAdultoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaHistoriaPersonalAdulto);
    }

    /**
     * DELETE  /plantilla-historia-personal-adultos/:id : delete the "id" plantillaHistoriaPersonalAdulto.
     *
     * @param id the id of the plantillaHistoriaPersonalAdulto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-historia-personal-adultos/{id}")
    public ResponseEntity<Void> deletePlantillaHistoriaPersonalAdulto(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaHistoriaPersonalAdulto : {}", id);
        plantillaHistoriaPersonalAdultoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
