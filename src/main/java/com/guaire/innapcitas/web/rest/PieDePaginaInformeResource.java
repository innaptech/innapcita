package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.PieDePaginaInforme;
import com.guaire.innapcitas.repository.PieDePaginaInformeRepository;
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
 * REST controller for managing PieDePaginaInforme.
 */
@RestController
@RequestMapping("/api")
public class PieDePaginaInformeResource {

    private final Logger log = LoggerFactory.getLogger(PieDePaginaInformeResource.class);

    private static final String ENTITY_NAME = "pieDePaginaInforme";

    private final PieDePaginaInformeRepository pieDePaginaInformeRepository;

    public PieDePaginaInformeResource(PieDePaginaInformeRepository pieDePaginaInformeRepository) {
        this.pieDePaginaInformeRepository = pieDePaginaInformeRepository;
    }

    /**
     * POST  /pie-de-pagina-informes : Create a new pieDePaginaInforme.
     *
     * @param pieDePaginaInforme the pieDePaginaInforme to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pieDePaginaInforme, or with status 400 (Bad Request) if the pieDePaginaInforme has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pie-de-pagina-informes")
    public ResponseEntity<PieDePaginaInforme> createPieDePaginaInforme(@Valid @RequestBody PieDePaginaInforme pieDePaginaInforme) throws URISyntaxException {
        log.debug("REST request to save PieDePaginaInforme : {}", pieDePaginaInforme);
        if (pieDePaginaInforme.getId() != null) {
            throw new BadRequestAlertException("A new pieDePaginaInforme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PieDePaginaInforme result = pieDePaginaInformeRepository.save(pieDePaginaInforme);
        return ResponseEntity.created(new URI("/api/pie-de-pagina-informes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pie-de-pagina-informes : Updates an existing pieDePaginaInforme.
     *
     * @param pieDePaginaInforme the pieDePaginaInforme to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pieDePaginaInforme,
     * or with status 400 (Bad Request) if the pieDePaginaInforme is not valid,
     * or with status 500 (Internal Server Error) if the pieDePaginaInforme couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pie-de-pagina-informes")
    public ResponseEntity<PieDePaginaInforme> updatePieDePaginaInforme(@Valid @RequestBody PieDePaginaInforme pieDePaginaInforme) throws URISyntaxException {
        log.debug("REST request to update PieDePaginaInforme : {}", pieDePaginaInforme);
        if (pieDePaginaInforme.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PieDePaginaInforme result = pieDePaginaInformeRepository.save(pieDePaginaInforme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pieDePaginaInforme.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pie-de-pagina-informes : get all the pieDePaginaInformes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pieDePaginaInformes in body
     */
    @GetMapping("/pie-de-pagina-informes")
    public ResponseEntity<List<PieDePaginaInforme>> getAllPieDePaginaInformes(Pageable pageable) {
        log.debug("REST request to get a page of PieDePaginaInformes");
        Page<PieDePaginaInforme> page = pieDePaginaInformeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pie-de-pagina-informes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /pie-de-pagina-informes/:id : get the "id" pieDePaginaInforme.
     *
     * @param id the id of the pieDePaginaInforme to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pieDePaginaInforme, or with status 404 (Not Found)
     */
    @GetMapping("/pie-de-pagina-informes/{id}")
    public ResponseEntity<PieDePaginaInforme> getPieDePaginaInforme(@PathVariable Long id) {
        log.debug("REST request to get PieDePaginaInforme : {}", id);
        Optional<PieDePaginaInforme> pieDePaginaInforme = pieDePaginaInformeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pieDePaginaInforme);
    }

    /**
     * DELETE  /pie-de-pagina-informes/:id : delete the "id" pieDePaginaInforme.
     *
     * @param id the id of the pieDePaginaInforme to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pie-de-pagina-informes/{id}")
    public ResponseEntity<Void> deletePieDePaginaInforme(@PathVariable Long id) {
        log.debug("REST request to delete PieDePaginaInforme : {}", id);
        pieDePaginaInformeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
