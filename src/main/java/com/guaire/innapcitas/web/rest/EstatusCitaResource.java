package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.EstatusCita;
import com.guaire.innapcitas.repository.EstatusCitaRepository;
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
 * REST controller for managing EstatusCita.
 */
@RestController
@RequestMapping("/api")
public class EstatusCitaResource {

    private final Logger log = LoggerFactory.getLogger(EstatusCitaResource.class);

    private static final String ENTITY_NAME = "estatusCita";

    private final EstatusCitaRepository estatusCitaRepository;

    public EstatusCitaResource(EstatusCitaRepository estatusCitaRepository) {
        this.estatusCitaRepository = estatusCitaRepository;
    }

    /**
     * POST  /estatus-citas : Create a new estatusCita.
     *
     * @param estatusCita the estatusCita to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estatusCita, or with status 400 (Bad Request) if the estatusCita has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estatus-citas")
    public ResponseEntity<EstatusCita> createEstatusCita(@Valid @RequestBody EstatusCita estatusCita) throws URISyntaxException {
        log.debug("REST request to save EstatusCita : {}", estatusCita);
        if (estatusCita.getId() != null) {
            throw new BadRequestAlertException("A new estatusCita cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstatusCita result = estatusCitaRepository.save(estatusCita);
        return ResponseEntity.created(new URI("/api/estatus-citas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estatus-citas : Updates an existing estatusCita.
     *
     * @param estatusCita the estatusCita to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estatusCita,
     * or with status 400 (Bad Request) if the estatusCita is not valid,
     * or with status 500 (Internal Server Error) if the estatusCita couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estatus-citas")
    public ResponseEntity<EstatusCita> updateEstatusCita(@Valid @RequestBody EstatusCita estatusCita) throws URISyntaxException {
        log.debug("REST request to update EstatusCita : {}", estatusCita);
        if (estatusCita.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstatusCita result = estatusCitaRepository.save(estatusCita);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estatusCita.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estatus-citas : get all the estatusCitas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of estatusCitas in body
     */
    @GetMapping("/estatus-citas")
    public ResponseEntity<List<EstatusCita>> getAllEstatusCitas(Pageable pageable) {
        log.debug("REST request to get a page of EstatusCitas");
        Page<EstatusCita> page = estatusCitaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estatus-citas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /estatus-citas/:id : get the "id" estatusCita.
     *
     * @param id the id of the estatusCita to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estatusCita, or with status 404 (Not Found)
     */
    @GetMapping("/estatus-citas/{id}")
    public ResponseEntity<EstatusCita> getEstatusCita(@PathVariable Long id) {
        log.debug("REST request to get EstatusCita : {}", id);
        Optional<EstatusCita> estatusCita = estatusCitaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(estatusCita);
    }

    /**
     * DELETE  /estatus-citas/:id : delete the "id" estatusCita.
     *
     * @param id the id of the estatusCita to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estatus-citas/{id}")
    public ResponseEntity<Void> deleteEstatusCita(@PathVariable Long id) {
        log.debug("REST request to delete EstatusCita : {}", id);
        estatusCitaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
