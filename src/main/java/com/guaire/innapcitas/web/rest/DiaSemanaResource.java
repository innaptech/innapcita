package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.DiaSemana;
import com.guaire.innapcitas.repository.DiaSemanaRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DiaSemana.
 */
@RestController
@RequestMapping("/api")
public class DiaSemanaResource {

    private final Logger log = LoggerFactory.getLogger(DiaSemanaResource.class);

    private static final String ENTITY_NAME = "diaSemana";

    private final DiaSemanaRepository diaSemanaRepository;


    public DiaSemanaResource(DiaSemanaRepository diaSemanaRepository) {
        this.diaSemanaRepository = diaSemanaRepository;
    }

    /**
     * POST  /dia-semanas : Create a new diaSemana.
     *
     * @param diaSemana the diaSemana to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diaSemana, or with status 400 (Bad Request) if the diaSemana has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dia-semanas")
    public ResponseEntity<DiaSemana> createDiaSemana(@RequestBody DiaSemana diaSemana) throws URISyntaxException {
        log.debug("REST request to save DiaSemana : {}", diaSemana);
        if (diaSemana.getId() != null) {
            throw new BadRequestAlertException("A new diaSemana cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiaSemana result = diaSemanaRepository.save(diaSemana);
        return ResponseEntity.created(new URI("/api/dia-semanas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dia-semanas : Updates an existing diaSemana.
     *
     * @param diaSemana the diaSemana to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diaSemana,
     * or with status 400 (Bad Request) if the diaSemana is not valid,
     * or with status 500 (Internal Server Error) if the diaSemana couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dia-semanas")
    public ResponseEntity<DiaSemana> updateDiaSemana(@RequestBody DiaSemana diaSemana) throws URISyntaxException {
        log.debug("REST request to update DiaSemana : {}", diaSemana);
        if (diaSemana.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiaSemana result = diaSemanaRepository.save(diaSemana);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diaSemana.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dia-semanas : get all the diaSemanas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diaSemanas in body
     */
    @GetMapping("/dia-semanas")
    public ResponseEntity<List<DiaSemana>> getAllDiaSemanas(Pageable pageable) {
        log.debug("REST request to get a page of DiaSemanas");
        Page<DiaSemana> page = diaSemanaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dia-semanas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dia-semanas/:id : get the "id" diaSemana.
     *
     * @param id the id of the diaSemana to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diaSemana, or with status 404 (Not Found)
     */
    @GetMapping("/dia-semanas/{id}")
    public ResponseEntity<DiaSemana> getDiaSemana(@PathVariable Long id) {
        log.debug("REST request to get DiaSemana : {}", id);
        Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(diaSemana);
    }

    /**
     * DELETE  /dia-semanas/:id : delete the "id" diaSemana.
     *
     * @param id the id of the diaSemana to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dia-semanas/{id}")
    public ResponseEntity<Void> deleteDiaSemana(@PathVariable Long id) {
        log.debug("REST request to delete DiaSemana : {}", id);
        diaSemanaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
