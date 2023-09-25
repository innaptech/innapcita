package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.Sexo;
import com.guaire.innapcitas.repository.SexoRepository;
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
 * REST controller for managing Sexo.
 */
@RestController
@RequestMapping("/api")
public class SexoResource {

    private final Logger log = LoggerFactory.getLogger(SexoResource.class);

    private static final String ENTITY_NAME = "sexo";

    private final SexoRepository sexoRepository;

    public SexoResource(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    /**
     * POST  /sexos : Create a new sexo.
     *
     * @param sexo the sexo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sexo, or with status 400 (Bad Request) if the sexo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sexos")
    public ResponseEntity<Sexo> createSexo(@Valid @RequestBody Sexo sexo) throws URISyntaxException {
        log.debug("REST request to save Sexo : {}", sexo);
        if (sexo.getId() != null) {
            throw new BadRequestAlertException("A new sexo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sexo result = sexoRepository.save(sexo);
        return ResponseEntity.created(new URI("/api/sexos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sexos : Updates an existing sexo.
     *
     * @param sexo the sexo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sexo,
     * or with status 400 (Bad Request) if the sexo is not valid,
     * or with status 500 (Internal Server Error) if the sexo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sexos")
    public ResponseEntity<Sexo> updateSexo(@Valid @RequestBody Sexo sexo) throws URISyntaxException {
        log.debug("REST request to update Sexo : {}", sexo);
        if (sexo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sexo result = sexoRepository.save(sexo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sexo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sexos : get all the sexos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sexos in body
     */
    @GetMapping("/sexos")
    public ResponseEntity<List<Sexo>> getAllSexos(Pageable pageable) {
        log.debug("REST request to get a page of Sexos");
        Page<Sexo> page = sexoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sexos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sexos/:id : get the "id" sexo.
     *
     * @param id the id of the sexo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sexo, or with status 404 (Not Found)
     */
    @GetMapping("/sexos/{id}")
    public ResponseEntity<Sexo> getSexo(@PathVariable Long id) {
        log.debug("REST request to get Sexo : {}", id);
        Optional<Sexo> sexo = sexoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sexo);
    }

    /**
     * DELETE  /sexos/:id : delete the "id" sexo.
     *
     * @param id the id of the sexo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sexos/{id}")
    public ResponseEntity<Void> deleteSexo(@PathVariable Long id) {
        log.debug("REST request to delete Sexo : {}", id);
        sexoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
