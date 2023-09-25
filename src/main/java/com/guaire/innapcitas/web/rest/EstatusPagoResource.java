package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.EstatusPago;
import com.guaire.innapcitas.repository.EstatusPagoRepository;
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

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing EstatusPago.
 */
@RestController
@RequestMapping("/api")
public class EstatusPagoResource {

    private final Logger log = LoggerFactory.getLogger(EstatusPagoResource.class);

    private static final String ENTITY_NAME = "estatusPago";

    private final EstatusPagoRepository estatusPagoRepository;


    public EstatusPagoResource(EstatusPagoRepository estatusPagoRepository) {
        this.estatusPagoRepository = estatusPagoRepository;
    }

    /**
     * POST  /estatus-pagos : Create a new estatusPago.
     *
     * @param estatusPago the estatusPago to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estatusPago, or with status 400 (Bad Request) if the estatusPago has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estatus-pagos")
    public ResponseEntity<EstatusPago> createEstatusPago(@Valid @RequestBody EstatusPago estatusPago) throws URISyntaxException {
        log.debug("REST request to save EstatusPago : {}", estatusPago);
        if (estatusPago.getId() != null) {
            throw new BadRequestAlertException("A new estatusPago cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstatusPago result = estatusPagoRepository.save(estatusPago);
        return ResponseEntity.created(new URI("/api/estatus-pagos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estatus-pagos : Updates an existing estatusPago.
     *
     * @param estatusPago the estatusPago to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estatusPago,
     * or with status 400 (Bad Request) if the estatusPago is not valid,
     * or with status 500 (Internal Server Error) if the estatusPago couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estatus-pagos")
    public ResponseEntity<EstatusPago> updateEstatusPago(@Valid @RequestBody EstatusPago estatusPago) throws URISyntaxException {
        log.debug("REST request to update EstatusPago : {}", estatusPago);
        if (estatusPago.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstatusPago result = estatusPagoRepository.save(estatusPago);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estatusPago.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estatus-pagos : get all the estatusPagos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of estatusPagos in body
     */
    @GetMapping("/estatus-pagos")
    public ResponseEntity<List<EstatusPago>> getAllEstatusPagos(Pageable pageable) {
        log.debug("REST request to get a page of EstatusPagos");
        Page<EstatusPago> page = estatusPagoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estatus-pagos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /estatus-pagos/:id : get the "id" estatusPago.
     *
     * @param id the id of the estatusPago to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estatusPago, or with status 404 (Not Found)
     */
    @GetMapping("/estatus-pagos/{id}")
    public ResponseEntity<EstatusPago> getEstatusPago(@PathVariable Long id) {
        log.debug("REST request to get EstatusPago : {}", id);
        Optional<EstatusPago> estatusPago = estatusPagoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(estatusPago);
    }

    /**
     * DELETE  /estatus-pagos/:id : delete the "id" estatusPago.
     *
     * @param id the id of the estatusPago to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estatus-pagos/{id}")
    public ResponseEntity<Void> deleteEstatusPago(@PathVariable Long id) {
        log.debug("REST request to delete EstatusPago : {}", id);
        estatusPagoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
