package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.GradoInstruccion;
import com.guaire.innapcitas.repository.GradoInstruccionRepository;
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
 * REST controller for managing GradoInstruccion.
 */
@RestController
@RequestMapping("/api")
public class GradoInstruccionResource {

    private final Logger log = LoggerFactory.getLogger(GradoInstruccionResource.class);

    private static final String ENTITY_NAME = "gradoInstruccion";

    private final GradoInstruccionRepository gradoInstruccionRepository;

    public GradoInstruccionResource(GradoInstruccionRepository gradoInstruccionRepository) {
        this.gradoInstruccionRepository = gradoInstruccionRepository;
    }

    /**
     * POST  /grado-instruccions : Create a new gradoInstruccion.
     *
     * @param gradoInstruccion the gradoInstruccion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gradoInstruccion, or with status 400 (Bad Request) if the gradoInstruccion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/grado-instruccions")
    public ResponseEntity<GradoInstruccion> createGradoInstruccion(@Valid @RequestBody GradoInstruccion gradoInstruccion) throws URISyntaxException {
        log.debug("REST request to save GradoInstruccion : {}", gradoInstruccion);
        if (gradoInstruccion.getId() != null) {
            throw new BadRequestAlertException("A new gradoInstruccion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GradoInstruccion result = gradoInstruccionRepository.save(gradoInstruccion);
        return ResponseEntity.created(new URI("/api/grado-instruccions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /grado-instruccions : Updates an existing gradoInstruccion.
     *
     * @param gradoInstruccion the gradoInstruccion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gradoInstruccion,
     * or with status 400 (Bad Request) if the gradoInstruccion is not valid,
     * or with status 500 (Internal Server Error) if the gradoInstruccion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/grado-instruccions")
    public ResponseEntity<GradoInstruccion> updateGradoInstruccion(@Valid @RequestBody GradoInstruccion gradoInstruccion) throws URISyntaxException {
        log.debug("REST request to update GradoInstruccion : {}", gradoInstruccion);
        if (gradoInstruccion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GradoInstruccion result = gradoInstruccionRepository.save(gradoInstruccion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gradoInstruccion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /grado-instruccions : get all the gradoInstruccions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gradoInstruccions in body
     */
    @GetMapping("/grado-instruccions")
    public ResponseEntity<List<GradoInstruccion>> getAllGradoInstruccions(Pageable pageable) {
        log.debug("REST request to get a page of GradoInstruccions");
        Page<GradoInstruccion> page = gradoInstruccionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/grado-instruccions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /grado-instruccions/:id : get the "id" gradoInstruccion.
     *
     * @param id the id of the gradoInstruccion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gradoInstruccion, or with status 404 (Not Found)
     */
    @GetMapping("/grado-instruccions/{id}")
    public ResponseEntity<GradoInstruccion> getGradoInstruccion(@PathVariable Long id) {
        log.debug("REST request to get GradoInstruccion : {}", id);
        Optional<GradoInstruccion> gradoInstruccion = gradoInstruccionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gradoInstruccion);
    }

    /**
     * DELETE  /grado-instruccions/:id : delete the "id" gradoInstruccion.
     *
     * @param id the id of the gradoInstruccion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/grado-instruccions/{id}")
    public ResponseEntity<Void> deleteGradoInstruccion(@PathVariable Long id) {
        log.debug("REST request to delete GradoInstruccion : {}", id);
        gradoInstruccionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
