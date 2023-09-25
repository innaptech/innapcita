package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.RangoHoras;
import com.guaire.innapcitas.repository.RangoHorasRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing RangoHoras.
 */
@RestController
@RequestMapping("/api")
public class RangoHorasResource {

    private final Logger log = LoggerFactory.getLogger(RangoHorasResource.class);

    private static final String ENTITY_NAME = "rangoHoras";

    private final RangoHorasRepository rangoHorasRepository;

    public RangoHorasResource(RangoHorasRepository rangoHorasRepository) {
        this.rangoHorasRepository = rangoHorasRepository;
    }

    /**
     * POST  /rango-horas : Create a new rangoHoras.
     *
     * @param rangoHoras the rangoHoras to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rangoHoras, or with status 400 (Bad Request) if the rangoHoras has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rango-horas")
    public ResponseEntity<RangoHoras> createRangoHoras(@Valid @RequestBody RangoHoras rangoHoras) throws URISyntaxException {
        log.debug("REST request to save RangoHoras : {}", rangoHoras);
        if (rangoHoras.getId() != null) {
            throw new BadRequestAlertException("A new rangoHoras cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RangoHoras result = rangoHorasRepository.save(rangoHoras);
        return ResponseEntity.created(new URI("/api/rango-horas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rango-horas : Updates an existing rangoHoras.
     *
     * @param rangoHoras the rangoHoras to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rangoHoras,
     * or with status 400 (Bad Request) if the rangoHoras is not valid,
     * or with status 500 (Internal Server Error) if the rangoHoras couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rango-horas")
    public ResponseEntity<RangoHoras> updateRangoHoras(@Valid @RequestBody RangoHoras rangoHoras) throws URISyntaxException {
        log.debug("REST request to update RangoHoras : {}", rangoHoras);
        if (rangoHoras.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RangoHoras result = rangoHorasRepository.save(rangoHoras);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rangoHoras.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rango-horas : get all the rangoHoras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rangoHoras in body
     */
    @GetMapping("/rango-horas")
    public ResponseEntity<List<RangoHoras>> getAllRangoHoras(Pageable pageable) {
        log.debug("REST request to get a page of RangoHoras");
        Page<RangoHoras> page = rangoHorasRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rango-horas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /rango-horas/:id : get the "id" rangoHoras.
     *
     * @param id the id of the rangoHoras to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rangoHoras, or with status 404 (Not Found)
     */
    @GetMapping("/rango-horas/{id}")
    public ResponseEntity<RangoHoras> getRangoHoras(@PathVariable Long id) {
        log.debug("REST request to get RangoHoras : {}", id);
        Optional<RangoHoras> rangoHoras = rangoHorasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rangoHoras);
    }

    /**
     * DELETE  /rango-horas/:id : delete the "id" rangoHoras.
     *
     * @param id the id of the rangoHoras to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rango-horas/{id}")
    public ResponseEntity<Void> deleteRangoHoras(@PathVariable Long id) {
        log.debug("REST request to delete RangoHoras : {}", id);
        rangoHorasRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
