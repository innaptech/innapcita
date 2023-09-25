package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.ExamenComplementario;
import com.guaire.innapcitas.repository.ExamenComplementarioRepository;
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
 * REST controller for managing ExamenComplementario.
 */
@RestController
@RequestMapping("/api")
public class ExamenComplementarioResource {

    private final Logger log = LoggerFactory.getLogger(ExamenComplementarioResource.class);

    private static final String ENTITY_NAME = "examenComplementario";

    private final ExamenComplementarioRepository examenComplementarioRepository;

    public ExamenComplementarioResource(ExamenComplementarioRepository examenComplementarioRepository) {
        this.examenComplementarioRepository = examenComplementarioRepository;
    }

    /**
     * POST  /examen-complementarios : Create a new examenComplementario.
     *
     * @param examenComplementario the examenComplementario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new examenComplementario, or with status 400 (Bad Request) if the examenComplementario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/examen-complementarios")
    public ResponseEntity<ExamenComplementario> createExamenComplementario(@Valid @RequestBody ExamenComplementario examenComplementario) throws URISyntaxException {
        log.debug("REST request to save ExamenComplementario : {}", examenComplementario);
        if (examenComplementario.getId() != null) {
            throw new BadRequestAlertException("A new examenComplementario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExamenComplementario result = examenComplementarioRepository.save(examenComplementario);
        return ResponseEntity.created(new URI("/api/examen-complementarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /examen-complementarios : Updates an existing examenComplementario.
     *
     * @param examenComplementario the examenComplementario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated examenComplementario,
     * or with status 400 (Bad Request) if the examenComplementario is not valid,
     * or with status 500 (Internal Server Error) if the examenComplementario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/examen-complementarios")
    public ResponseEntity<ExamenComplementario> updateExamenComplementario(@Valid @RequestBody ExamenComplementario examenComplementario) throws URISyntaxException {
        log.debug("REST request to update ExamenComplementario : {}", examenComplementario);
        if (examenComplementario.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExamenComplementario result = examenComplementarioRepository.save(examenComplementario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, examenComplementario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /examen-complementarios : get all the examenComplementarios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of examenComplementarios in body
     */
    @GetMapping("/examen-complementarios")
    public ResponseEntity<List<ExamenComplementario>> getAllExamenComplementarios(Pageable pageable) {
        log.debug("REST request to get a page of ExamenComplementarios");
        Page<ExamenComplementario> page = examenComplementarioRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/examen-complementarios");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /examen-complementarios/:id : get the "id" examenComplementario.
     *
     * @param id the id of the examenComplementario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the examenComplementario, or with status 404 (Not Found)
     */
    @GetMapping("/examen-complementarios/{id}")
    public ResponseEntity<ExamenComplementario> getExamenComplementario(@PathVariable Long id) {
        log.debug("REST request to get ExamenComplementario : {}", id);
        Optional<ExamenComplementario> examenComplementario = examenComplementarioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(examenComplementario);
    }

    /**
     * DELETE  /examen-complementarios/:id : delete the "id" examenComplementario.
     *
     * @param id the id of the examenComplementario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/examen-complementarios/{id}")
    public ResponseEntity<Void> deleteExamenComplementario(@PathVariable Long id) {
        log.debug("REST request to delete ExamenComplementario : {}", id);
        examenComplementarioRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
