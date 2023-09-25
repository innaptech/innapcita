package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.EspecialidadMedico;
import com.guaire.innapcitas.repository.EspecialidadMedicoRepository;
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
 * REST controller for managing EspecialidadMedico.
 */
@RestController
@RequestMapping("/api")
public class EspecialidadMedicoResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadMedicoResource.class);

    private static final String ENTITY_NAME = "especialidadMedico";

    private final EspecialidadMedicoRepository especialidadMedicoRepository;

    public EspecialidadMedicoResource(EspecialidadMedicoRepository especialidadMedicoRepository) {
        this.especialidadMedicoRepository = especialidadMedicoRepository;
    }

    /**
     * POST  /especialidad-medicos : Create a new especialidadMedico.
     *
     * @param especialidadMedico the especialidadMedico to create
     * @return the ResponseEntity with status 201 (Created) and with body the new especialidadMedico, or with status 400 (Bad Request) if the especialidadMedico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/especialidad-medicos")
    public ResponseEntity<EspecialidadMedico> createEspecialidadMedico(@Valid @RequestBody EspecialidadMedico especialidadMedico) throws URISyntaxException {
        log.debug("REST request to save EspecialidadMedico : {}", especialidadMedico);
        if (especialidadMedico.getId() != null) {
            throw new BadRequestAlertException("A new especialidadMedico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspecialidadMedico result = especialidadMedicoRepository.save(especialidadMedico);
        return ResponseEntity.created(new URI("/api/especialidad-medicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /especialidad-medicos : Updates an existing especialidadMedico.
     *
     * @param especialidadMedico the especialidadMedico to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated especialidadMedico,
     * or with status 400 (Bad Request) if the especialidadMedico is not valid,
     * or with status 500 (Internal Server Error) if the especialidadMedico couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/especialidad-medicos")
    public ResponseEntity<EspecialidadMedico> updateEspecialidadMedico(@Valid @RequestBody EspecialidadMedico especialidadMedico) throws URISyntaxException {
        log.debug("REST request to update EspecialidadMedico : {}", especialidadMedico);
        if (especialidadMedico.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspecialidadMedico result = especialidadMedicoRepository.save(especialidadMedico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, especialidadMedico.getId().toString()))
            .body(result);
    }

    /**
     * GET  /especialidad-medicos : get all the especialidadMedicos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of especialidadMedicos in body
     */
    @GetMapping("/especialidad-medicos")
    public ResponseEntity<List<EspecialidadMedico>> getAllEspecialidadMedicos(Pageable pageable) {
        log.debug("REST request to get a page of EspecialidadMedicos");
        Page<EspecialidadMedico> page = especialidadMedicoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidad-medicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /especialidad-medicos : get all the especialidadMedicos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of especialidadMedicos in body
     */
    @GetMapping("/especialidad-medicos/search/{query}")
    public ResponseEntity<List<EspecialidadMedico>> getAllEspecialidadMedicosSearch(@PathVariable String query, Pageable pageable) {
        log.debug("REST request to get a page of EspecialidadMedicos");
        Page<EspecialidadMedico> page = especialidadMedicoRepository.findAllByDescripcionStartsWithIgnoreCaseOrMedico_LastNameStartsWithIgnoreCaseOrMedico_FirstNameStartsWithIgnoreCaseOrEspecialidad_EspecialidadContainsIgnoreCase(query, query, query, query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidad-medicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /especialidad-medicos/:id : get the "id" especialidadMedico.
     *
     * @param id the id of the especialidadMedico to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the especialidadMedico, or with status 404 (Not Found)
     */
    @GetMapping("/especialidad-medicos/{id}")
    public ResponseEntity<EspecialidadMedico> getEspecialidadMedico(@PathVariable Long id) {
        log.debug("REST request to get EspecialidadMedico : {}", id);
        Optional<EspecialidadMedico> especialidadMedico = especialidadMedicoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(especialidadMedico);
    }

    /**
     * DELETE  /especialidad-medicos/:id : delete the "id" especialidadMedico.
     *
     * @param id the id of the especialidadMedico to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/especialidad-medicos/{id}")
    public ResponseEntity<Void> deleteEspecialidadMedico(@PathVariable Long id) {
        log.debug("REST request to delete EspecialidadMedico : {}", id);
        especialidadMedicoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /especialidad-medicos : get all the especialidadMedicos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of especialidadMedicos in body
     */
    @GetMapping("/especialidad-medicos/medico")
    public ResponseEntity<List<EspecialidadMedico>> getMedicoEspecialidadMedicos(Pageable pageable) {
        log.debug("REST request to get a page of EspecialidadMedicos");
        Page<EspecialidadMedico> page = especialidadMedicoRepository.findByMedicoIsCurrentUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especialidad-medicos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
