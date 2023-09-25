package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.TipoConsulta;
import com.guaire.innapcitas.repository.TipoConsultaRepository;
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
 * REST controller for managing TipoConsulta.
 */
@RestController
@RequestMapping("/api")
public class TipoConsultaResource {

    private final Logger log = LoggerFactory.getLogger(TipoConsultaResource.class);

    private static final String ENTITY_NAME = "tipoConsulta";

    private final TipoConsultaRepository tipoConsultaRepository;

    public TipoConsultaResource(TipoConsultaRepository tipoConsultaRepository) {
        this.tipoConsultaRepository = tipoConsultaRepository;
    }

    /**
     * POST  /tipo-consultas : Create a new tipoConsulta.
     *
     * @param tipoConsulta the tipoConsulta to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoConsulta, or with status 400 (Bad Request) if the tipoConsulta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-consultas")
    public ResponseEntity<TipoConsulta> createTipoConsulta(@Valid @RequestBody TipoConsulta tipoConsulta) throws URISyntaxException {
        log.debug("REST request to save TipoConsulta : {}", tipoConsulta);
        if (tipoConsulta.getId() != null) {
            throw new BadRequestAlertException("A new tipoConsulta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoConsulta result = tipoConsultaRepository.save(tipoConsulta);
        return ResponseEntity.created(new URI("/api/tipo-consultas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-consultas : Updates an existing tipoConsulta.
     *
     * @param tipoConsulta the tipoConsulta to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoConsulta,
     * or with status 400 (Bad Request) if the tipoConsulta is not valid,
     * or with status 500 (Internal Server Error) if the tipoConsulta couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-consultas")
    public ResponseEntity<TipoConsulta> updateTipoConsulta(@Valid @RequestBody TipoConsulta tipoConsulta) throws URISyntaxException {
        log.debug("REST request to update TipoConsulta : {}", tipoConsulta);
        if (tipoConsulta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoConsulta result = tipoConsultaRepository.save(tipoConsulta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoConsulta.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-consultas : get all the tipoConsultas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tipoConsultas in body
     */
    @GetMapping("/tipo-consultas")
    public ResponseEntity<List<TipoConsulta>> getAllTipoConsultas(Pageable pageable) {
        log.debug("REST request to get a page of TipoConsultas");
        Page<TipoConsulta> page = tipoConsultaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-consultas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-consultas/:id : get the "id" tipoConsulta.
     *
     * @param id the id of the tipoConsulta to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoConsulta, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-consultas/{id}")
    public ResponseEntity<TipoConsulta> getTipoConsulta(@PathVariable Long id) {
        log.debug("REST request to get TipoConsulta : {}", id);
        Optional<TipoConsulta> tipoConsulta = tipoConsultaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoConsulta);
    }

    /**
     * DELETE  /tipo-consultas/:id : delete the "id" tipoConsulta.
     *
     * @param id the id of the tipoConsulta to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-consultas/{id}")
    public ResponseEntity<Void> deleteTipoConsulta(@PathVariable Long id) {
        log.debug("REST request to delete TipoConsulta : {}", id);
        tipoConsultaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
