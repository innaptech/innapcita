package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.CabeceraInforme;
import com.guaire.innapcitas.repository.CabeceraInformeRepository;
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
 * REST controller for managing CabeceraInforme.
 */
@RestController
@RequestMapping("/api")
public class CabeceraInformeResource {

    private final Logger log = LoggerFactory.getLogger(CabeceraInformeResource.class);

    private static final String ENTITY_NAME = "cabeceraInforme";

    private final CabeceraInformeRepository cabeceraInformeRepository;

    public CabeceraInformeResource(CabeceraInformeRepository cabeceraInformeRepository) {
        this.cabeceraInformeRepository = cabeceraInformeRepository;
    }

    /**
     * POST  /cabecera-informes : Create a new cabeceraInforme.
     *
     * @param cabeceraInforme the cabeceraInforme to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cabeceraInforme, or with status 400 (Bad Request) if the cabeceraInforme has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cabecera-informes")
    public ResponseEntity<CabeceraInforme> createCabeceraInforme(@Valid @RequestBody CabeceraInforme cabeceraInforme) throws URISyntaxException {
        log.debug("REST request to save CabeceraInforme : {}", cabeceraInforme);
        if (cabeceraInforme.getId() != null) {
            throw new BadRequestAlertException("A new cabeceraInforme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CabeceraInforme result = cabeceraInformeRepository.save(cabeceraInforme);
        return ResponseEntity.created(new URI("/api/cabecera-informes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cabecera-informes : Updates an existing cabeceraInforme.
     *
     * @param cabeceraInforme the cabeceraInforme to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cabeceraInforme,
     * or with status 400 (Bad Request) if the cabeceraInforme is not valid,
     * or with status 500 (Internal Server Error) if the cabeceraInforme couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cabecera-informes")
    public ResponseEntity<CabeceraInforme> updateCabeceraInforme(@Valid @RequestBody CabeceraInforme cabeceraInforme) throws URISyntaxException {
        log.debug("REST request to update CabeceraInforme : {}", cabeceraInforme);
        if (cabeceraInforme.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CabeceraInforme result = cabeceraInformeRepository.save(cabeceraInforme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cabeceraInforme.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cabecera-informes : get all the cabeceraInformes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cabeceraInformes in body
     */
    @GetMapping("/cabecera-informes")
    public ResponseEntity<List<CabeceraInforme>> getAllCabeceraInformes(Pageable pageable) {
        log.debug("REST request to get a page of CabeceraInformes");
        Page<CabeceraInforme> page = cabeceraInformeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cabecera-informes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /cabecera-informes/:id : get the "id" cabeceraInforme.
     *
     * @param id the id of the cabeceraInforme to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cabeceraInforme, or with status 404 (Not Found)
     */
    @GetMapping("/cabecera-informes/{id}")
    public ResponseEntity<CabeceraInforme> getCabeceraInforme(@PathVariable Long id) {
        log.debug("REST request to get CabeceraInforme : {}", id);
        Optional<CabeceraInforme> cabeceraInforme = cabeceraInformeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cabeceraInforme);
    }

    /**
     * DELETE  /cabecera-informes/:id : delete the "id" cabeceraInforme.
     *
     * @param id the id of the cabeceraInforme to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cabecera-informes/{id}")
    public ResponseEntity<Void> deleteCabeceraInforme(@PathVariable Long id) {
        log.debug("REST request to delete CabeceraInforme : {}", id);
        cabeceraInformeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
