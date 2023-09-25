package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.TipoPago;
import com.guaire.innapcitas.repository.TipoPagoRepository;
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
 * REST controller for managing TipoPago.
 */
@RestController
@RequestMapping("/api")
public class TipoPagoResource {

    private final Logger log = LoggerFactory.getLogger(TipoPagoResource.class);

    private static final String ENTITY_NAME = "tipoPago";

    private final TipoPagoRepository tipoPagoRepository;

    public TipoPagoResource(TipoPagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    /**
     * POST  /tipo-pagos : Create a new tipoPago.
     *
     * @param tipoPago the tipoPago to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoPago, or with status 400 (Bad Request) if the tipoPago has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-pagos")
    public ResponseEntity<TipoPago> createTipoPago(@Valid @RequestBody TipoPago tipoPago) throws URISyntaxException {
        log.debug("REST request to save TipoPago : {}", tipoPago);
        if (tipoPago.getId() != null) {
            throw new BadRequestAlertException("A new tipoPago cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoPago result = tipoPagoRepository.save(tipoPago);
        return ResponseEntity.created(new URI("/api/tipo-pagos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-pagos : Updates an existing tipoPago.
     *
     * @param tipoPago the tipoPago to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoPago,
     * or with status 400 (Bad Request) if the tipoPago is not valid,
     * or with status 500 (Internal Server Error) if the tipoPago couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-pagos")
    public ResponseEntity<TipoPago> updateTipoPago(@Valid @RequestBody TipoPago tipoPago) throws URISyntaxException {
        log.debug("REST request to update TipoPago : {}", tipoPago);
        if (tipoPago.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoPago result = tipoPagoRepository.save(tipoPago);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoPago.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-pagos : get all the tipoPagos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tipoPagos in body
     */
    @GetMapping("/tipo-pagos")
    public ResponseEntity<List<TipoPago>> getAllTipoPagos(Pageable pageable) {
        log.debug("REST request to get a page of TipoPagos");
        Page<TipoPago> page = tipoPagoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-pagos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tipo-pagos/:id : get the "id" tipoPago.
     *
     * @param id the id of the tipoPago to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoPago, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-pagos/{id}")
    public ResponseEntity<TipoPago> getTipoPago(@PathVariable Long id) {
        log.debug("REST request to get TipoPago : {}", id);
        Optional<TipoPago> tipoPago = tipoPagoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoPago);
    }

    /**
     * DELETE  /tipo-pagos/:id : delete the "id" tipoPago.
     *
     * @param id the id of the tipoPago to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-pagos/{id}")
    public ResponseEntity<Void> deleteTipoPago(@PathVariable Long id) {
        log.debug("REST request to delete TipoPago : {}", id);
        tipoPagoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
