package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.MonedaCambio;
import com.guaire.innapcitas.repository.MonedaCambioRepository;
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
 * REST controller for managing MonedaCambio.
 */
@RestController
@RequestMapping("/api")
public class MonedaCambioResource {

    private final Logger log = LoggerFactory.getLogger(MonedaCambioResource.class);

    private static final String ENTITY_NAME = "monedaCambio";

    private final MonedaCambioRepository monedaCambioRepository;

    public MonedaCambioResource(MonedaCambioRepository monedaCambioRepository) {
        this.monedaCambioRepository = monedaCambioRepository;
    }

    /**
     * POST  /moneda-cambios : Create a new monedaCambio.
     *
     * @param monedaCambio the monedaCambio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monedaCambio, or with status 400 (Bad Request) if the monedaCambio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/moneda-cambios")
    public ResponseEntity<MonedaCambio> createMonedaCambio(@Valid @RequestBody MonedaCambio monedaCambio) throws URISyntaxException {
        log.debug("REST request to save MonedaCambio : {}", monedaCambio);
        if (monedaCambio.getId() != null) {
            throw new BadRequestAlertException("A new monedaCambio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonedaCambio result = monedaCambioRepository.save(monedaCambio);
        return ResponseEntity.created(new URI("/api/moneda-cambios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /moneda-cambios : Updates an existing monedaCambio.
     *
     * @param monedaCambio the monedaCambio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monedaCambio,
     * or with status 400 (Bad Request) if the monedaCambio is not valid,
     * or with status 500 (Internal Server Error) if the monedaCambio couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/moneda-cambios")
    public ResponseEntity<MonedaCambio> updateMonedaCambio(@Valid @RequestBody MonedaCambio monedaCambio) throws URISyntaxException {
        log.debug("REST request to update MonedaCambio : {}", monedaCambio);
        if (monedaCambio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonedaCambio result = monedaCambioRepository.save(monedaCambio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monedaCambio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /moneda-cambios : get all the monedaCambios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of monedaCambios in body
     */
    @GetMapping("/moneda-cambios")
    public ResponseEntity<List<MonedaCambio>> getAllMonedaCambios(Pageable pageable) {
        log.debug("REST request to get a page of MonedaCambios");
        Page<MonedaCambio> page = monedaCambioRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/moneda-cambios");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /moneda-cambios/:id : get the "id" monedaCambio.
     *
     * @param id the id of the monedaCambio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monedaCambio, or with status 404 (Not Found)
     */
    @GetMapping("/moneda-cambios/{id}")
    public ResponseEntity<MonedaCambio> getMonedaCambio(@PathVariable Long id) {
        log.debug("REST request to get MonedaCambio : {}", id);
        Optional<MonedaCambio> monedaCambio = monedaCambioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(monedaCambio);
    }

    /**
     * DELETE  /moneda-cambios/:id : delete the "id" monedaCambio.
     *
     * @param id the id of the monedaCambio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/moneda-cambios/{id}")
    public ResponseEntity<Void> deleteMonedaCambio(@PathVariable Long id) {
        log.debug("REST request to delete MonedaCambio : {}", id);
        monedaCambioRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
