package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.Especialidad;
import com.guaire.innapcitas.repository.EspecialidadRepository;
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
 * REST controller for managing Especialidad.
 */
@RestController
@RequestMapping("/api")
public class EspecialidadResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadResource.class);

    private static final String ENTITY_NAME = "especialidad";

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadResource(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    /**
     * POST  /especialidads : Create a new especialidad.
     *
     * @param especialidad the especialidad to create
     * @return the ResponseEntity with status 201 (Created) and with body the new especialidad, or with status 400 (Bad Request) if the especialidad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/especialidads")
    public ResponseEntity<Especialidad> createEspecialidad(@Valid @RequestBody Especialidad especialidad) throws URISyntaxException {
        log.debug("REST request to save Especialidad : {}", especialidad);
        if (especialidad.getId() != null) {
            throw new BadRequestAlertException("A new especialidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Especialidad result = especialidadRepository.save(especialidad);
        return ResponseEntity.created(new URI("/api/especialidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /especialidads : Updates an existing especialidad.
     *
     * @param especialidad the especialidad to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated especialidad,
     * or with status 400 (Bad Request) if the especialidad is not valid,
     * or with status 500 (Internal Server Error) if the especialidad couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/especialidads")
    public ResponseEntity<Especialidad> updateEspecialidad(@Valid @RequestBody Especialidad especialidad) throws URISyntaxException {
        log.debug("REST request to update Especialidad : {}", especialidad);
        if (especialidad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Especialidad result = especialidadRepository.save(especialidad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, especialidad.getId().toString()))
            .body(result);
    }

    /**
     * GET  /especialidads : get all the especialidads.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of especialidads in body
     */
    @GetMapping("/especialidads")
    public ResponseEntity<List<Especialidad>> getAllEspecialidads(Pageable pageable) {
        log.debug("REST request to get a page of Especialidads");
        Page<Especialidad> page = especialidadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidads");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /especialidads/:id : get the "id" especialidad.
     *
     * @param id the id of the especialidad to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the especialidad, or with status 404 (Not Found)
     */
    @GetMapping("/especialidads/{id}")
    public ResponseEntity<Especialidad> getEspecialidad(@PathVariable Long id) {
        log.debug("REST request to get Especialidad : {}", id);
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(especialidad);
    }

    /**
     * DELETE  /especialidads/:id : delete the "id" especialidad.
     *
     * @param id the id of the especialidad to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/especialidads/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Long id) {
        log.debug("REST request to delete Especialidad : {}", id);
        especialidadRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
