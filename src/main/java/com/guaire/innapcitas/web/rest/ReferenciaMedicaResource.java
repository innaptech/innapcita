package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.ReferenciaMedica;
import com.guaire.innapcitas.repository.ReferenciaMedicaRepository;
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
 * REST controller for managing ReferenciaMedica.
 */
@RestController
@RequestMapping("/api")
public class ReferenciaMedicaResource {

    private final Logger log = LoggerFactory.getLogger(ReferenciaMedicaResource.class);

    private static final String ENTITY_NAME = "referenciaMedica";

    private final ReferenciaMedicaRepository referenciaMedicaRepository;

    public ReferenciaMedicaResource(ReferenciaMedicaRepository referenciaMedicaRepository) {
        this.referenciaMedicaRepository = referenciaMedicaRepository;
    }

    /**
     * POST  /referencia-medicas : Create a new referenciaMedica.
     *
     * @param referenciaMedica the referenciaMedica to create
     * @return the ResponseEntity with status 201 (Created) and with body the new referenciaMedica, or with status 400 (Bad Request) if the referenciaMedica has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/referencia-medicas")
    public ResponseEntity<ReferenciaMedica> createReferenciaMedica(@Valid @RequestBody ReferenciaMedica referenciaMedica) throws URISyntaxException {
        log.debug("REST request to save ReferenciaMedica : {}", referenciaMedica);
        if (referenciaMedica.getId() != null) {
            throw new BadRequestAlertException("A new referenciaMedica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReferenciaMedica result = referenciaMedicaRepository.save(referenciaMedica);
        return ResponseEntity.created(new URI("/api/referencia-medicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /referencia-medicas : Updates an existing referenciaMedica.
     *
     * @param referenciaMedica the referenciaMedica to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated referenciaMedica,
     * or with status 400 (Bad Request) if the referenciaMedica is not valid,
     * or with status 500 (Internal Server Error) if the referenciaMedica couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/referencia-medicas")
    public ResponseEntity<ReferenciaMedica> updateReferenciaMedica(@Valid @RequestBody ReferenciaMedica referenciaMedica) throws URISyntaxException {
        log.debug("REST request to update ReferenciaMedica : {}", referenciaMedica);
        if (referenciaMedica.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReferenciaMedica result = referenciaMedicaRepository.save(referenciaMedica);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, referenciaMedica.getId().toString()))
            .body(result);
    }

    /**
     * GET  /referencia-medicas : get all the referenciaMedicas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of referenciaMedicas in body
     */
    @GetMapping("/referencia-medicas")
    public ResponseEntity<List<ReferenciaMedica>> getAllReferenciaMedicas(Pageable pageable) {
        log.debug("REST request to get a page of ReferenciaMedicas");
        Page<ReferenciaMedica> page = referenciaMedicaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/referencia-medicas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /referencia-medicas/:id : get the "id" referenciaMedica.
     *
     * @param id the id of the referenciaMedica to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the referenciaMedica, or with status 404 (Not Found)
     */
    @GetMapping("/referencia-medicas/{id}")
    public ResponseEntity<ReferenciaMedica> getReferenciaMedica(@PathVariable Long id) {
        log.debug("REST request to get ReferenciaMedica : {}", id);
        Optional<ReferenciaMedica> referenciaMedica = referenciaMedicaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(referenciaMedica);
    }

    /**
     * DELETE  /referencia-medicas/:id : delete the "id" referenciaMedica.
     *
     * @param id the id of the referenciaMedica to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/referencia-medicas/{id}")
    public ResponseEntity<Void> deleteReferenciaMedica(@PathVariable Long id) {
        log.debug("REST request to delete ReferenciaMedica : {}", id);
        referenciaMedicaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
