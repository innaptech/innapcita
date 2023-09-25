package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PlantillaHistoriaMedicaAdulto;
import com.guaire.innapcitas.repository.PlantillaHistoriaMedicaAdultoRepository;
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
 * REST controller for managing PlantillaHistoriaMedicaAdulto.
 */
@RestController
@RequestMapping("/api")
public class PlantillaHistoriaMedicaAdultoResource {

    private final Logger log = LoggerFactory.getLogger(PlantillaHistoriaMedicaAdultoResource.class);

    private static final String ENTITY_NAME = "plantillaHistoriaMedicaAdulto";

    private final PlantillaHistoriaMedicaAdultoRepository plantillaHistoriaMedicaAdultoRepository;

    public PlantillaHistoriaMedicaAdultoResource(PlantillaHistoriaMedicaAdultoRepository plantillaHistoriaMedicaAdultoRepository) {
        this.plantillaHistoriaMedicaAdultoRepository = plantillaHistoriaMedicaAdultoRepository;
    }

    /**
     * POST  /plantilla-historia-medica-adultos : Create a new plantillaHistoriaMedicaAdulto.
     *
     * @param plantillaHistoriaMedicaAdulto the plantillaHistoriaMedicaAdulto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new plantillaHistoriaMedicaAdulto, or with status 400 (Bad Request) if the plantillaHistoriaMedicaAdulto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plantilla-historia-medica-adultos")
    public ResponseEntity<PlantillaHistoriaMedicaAdulto> createPlantillaHistoriaMedicaAdulto(@RequestBody PlantillaHistoriaMedicaAdulto plantillaHistoriaMedicaAdulto) throws URISyntaxException {
        log.debug("REST request to save PlantillaHistoriaMedicaAdulto : {}", plantillaHistoriaMedicaAdulto);
        if (plantillaHistoriaMedicaAdulto.getId() != null) {
            throw new BadRequestAlertException("A new plantillaHistoriaMedicaAdulto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlantillaHistoriaMedicaAdulto result = plantillaHistoriaMedicaAdultoRepository.save(plantillaHistoriaMedicaAdulto);
        return ResponseEntity.created(new URI("/api/plantilla-historia-medica-adultos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plantilla-historia-medica-adultos : Updates an existing plantillaHistoriaMedicaAdulto.
     *
     * @param plantillaHistoriaMedicaAdulto the plantillaHistoriaMedicaAdulto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated plantillaHistoriaMedicaAdulto,
     * or with status 400 (Bad Request) if the plantillaHistoriaMedicaAdulto is not valid,
     * or with status 500 (Internal Server Error) if the plantillaHistoriaMedicaAdulto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plantilla-historia-medica-adultos")
    public ResponseEntity<PlantillaHistoriaMedicaAdulto> updatePlantillaHistoriaMedicaAdulto(@RequestBody PlantillaHistoriaMedicaAdulto plantillaHistoriaMedicaAdulto) throws URISyntaxException {
        log.debug("REST request to update PlantillaHistoriaMedicaAdulto : {}", plantillaHistoriaMedicaAdulto);
        if (plantillaHistoriaMedicaAdulto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlantillaHistoriaMedicaAdulto result = plantillaHistoriaMedicaAdultoRepository.save(plantillaHistoriaMedicaAdulto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, plantillaHistoriaMedicaAdulto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plantilla-historia-medica-adultos : get all the plantillaHistoriaMedicaAdultos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of plantillaHistoriaMedicaAdultos in body
     */
    @GetMapping("/plantilla-historia-medica-adultos")
    public ResponseEntity<List<PlantillaHistoriaMedicaAdulto>> getAllPlantillaHistoriaMedicaAdultos(Pageable pageable) {
        log.debug("REST request to get a page of PlantillaHistoriaMedicaAdultos");
        Page<PlantillaHistoriaMedicaAdulto> page = plantillaHistoriaMedicaAdultoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plantilla-historia-medica-adultos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /plantilla-historia-medica-adultos/:id : get the "id" plantillaHistoriaMedicaAdulto.
     *
     * @param id the id of the plantillaHistoriaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the plantillaHistoriaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/plantilla-historia-medica-adultos/{id}")
    public ResponseEntity<PlantillaHistoriaMedicaAdulto> getPlantillaHistoriaMedicaAdulto(@PathVariable Long id) {
        log.debug("REST request to get PlantillaHistoriaMedicaAdulto : {}", id);
        Optional<PlantillaHistoriaMedicaAdulto> plantillaHistoriaMedicaAdulto = plantillaHistoriaMedicaAdultoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(plantillaHistoriaMedicaAdulto);
    }

    /**
     * DELETE  /plantilla-historia-medica-adultos/:id : delete the "id" plantillaHistoriaMedicaAdulto.
     *
     * @param id the id of the plantillaHistoriaMedicaAdulto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plantilla-historia-medica-adultos/{id}")
    public ResponseEntity<Void> deletePlantillaHistoriaMedicaAdulto(@PathVariable Long id) {
        log.debug("REST request to delete PlantillaHistoriaMedicaAdulto : {}", id);
        plantillaHistoriaMedicaAdultoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
