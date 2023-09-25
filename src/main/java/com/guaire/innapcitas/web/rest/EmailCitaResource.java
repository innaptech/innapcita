package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.EmailCita;
import com.guaire.innapcitas.repository.EmailCitaRepository;
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

/**
 * REST controller for managing EmailCita.
 */
@RestController
@RequestMapping("/api")
public class EmailCitaResource {

    private final Logger log = LoggerFactory.getLogger(EmailCitaResource.class);

    private static final String ENTITY_NAME = "emailCita";

    private final EmailCitaRepository emailCitaRepository;

    public EmailCitaResource(EmailCitaRepository emailCitaRepository) {
        this.emailCitaRepository = emailCitaRepository;
    }

    /**
     * POST  /email-citas : Create a new emailCita.
     *
     * @param emailCita the emailCita to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emailCita, or with status 400 (Bad Request) if the emailCita has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/email-citas")
    public ResponseEntity<EmailCita> createEmailCita(@RequestBody EmailCita emailCita) throws URISyntaxException {
        log.debug("REST request to save EmailCita : {}", emailCita);
        if (emailCita.getId() != null) {
            throw new BadRequestAlertException("A new emailCita cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailCita result = emailCitaRepository.save(emailCita);
        return ResponseEntity.created(new URI("/api/email-citas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /email-citas : Updates an existing emailCita.
     *
     * @param emailCita the emailCita to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emailCita,
     * or with status 400 (Bad Request) if the emailCita is not valid,
     * or with status 500 (Internal Server Error) if the emailCita couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/email-citas")
    public ResponseEntity<EmailCita> updateEmailCita(@RequestBody EmailCita emailCita) throws URISyntaxException {
        log.debug("REST request to update EmailCita : {}", emailCita);
        if (emailCita.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmailCita result = emailCitaRepository.save(emailCita);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emailCita.getId().toString()))
            .body(result);
    }

    /**
     * GET  /email-citas : get all the emailCitas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of emailCitas in body
     */
    @GetMapping("/email-citas")
    public ResponseEntity<List<EmailCita>> getAllEmailCitas(Pageable pageable) {
        log.debug("REST request to get a page of EmailCitas");
        Page<EmailCita> page = emailCitaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/email-citas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /email-citas/:id : get the "id" emailCita.
     *
     * @param id the id of the emailCita to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emailCita, or with status 404 (Not Found)
     */
    @GetMapping("/email-citas/{id}")
    public ResponseEntity<EmailCita> getEmailCita(@PathVariable Long id) {
        log.debug("REST request to get EmailCita : {}", id);
        Optional<EmailCita> emailCita = emailCitaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(emailCita);
    }

    /**
     * DELETE  /email-citas/:id : delete the "id" emailCita.
     *
     * @param id the id of the emailCita to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/email-citas/{id}")
    public ResponseEntity<Void> deleteEmailCita(@PathVariable Long id) {
        log.debug("REST request to delete EmailCita : {}", id);
        emailCitaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
