package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.Bitacora;
import com.guaire.innapcitas.domain.User;
import com.guaire.innapcitas.repository.BitacoraRepository;
import com.guaire.innapcitas.repository.UserRepository;
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
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Bitacora.
 */
@RestController
@RequestMapping("/api")
public class BitacoraResource {

    private final Logger log = LoggerFactory.getLogger(BitacoraResource.class);

    private static final String ENTITY_NAME = "bitacora";

    private final BitacoraRepository bitacoraRepository;

    private final UserRepository userRepository;

    public BitacoraResource(BitacoraRepository bitacoraRepository, UserRepository userRepository) {
        this.bitacoraRepository = bitacoraRepository;
        this.userRepository = userRepository;
    }

    /**
     * POST  /bitacoras : Create a new bitacora.
     *
     * @param bitacora the bitacora to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bitacora, or with status 400 (Bad Request) if the bitacora has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bitacoras")
    public ResponseEntity<Bitacora> createBitacora(@Valid @RequestBody Bitacora bitacora) throws URISyntaxException {
        log.debug("REST request to save Bitacora : {}", bitacora);
        if (bitacora.getId() != null) {
            throw new BadRequestAlertException("A new bitacora cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bitacora result = bitacoraRepository.save(bitacora);
        return ResponseEntity.created(new URI("/api/bitacoras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    public void newBitacora(String login, String titulo, String detalle, Object entity, Object oldEntity, Integer tipo) {
        Bitacora bitacora = new Bitacora();

        try {
            bitacora.setUser(userRepository.findOneByLogin(login).get());
            bitacora.setTitulo(titulo);

            String detalleGuardar = "";
            if (detalle != null)
                detalleGuardar = detalle;
            if(entity != null) {
                detalleGuardar += "<br><br><b>Entidad:</b>";
                Object someObject = entity;
                for (Field field : someObject.getClass().getDeclaredFields()) {
                    field.setAccessible(true); // You might want to set modifier to public first.
                    Object value = field.get(someObject);
                    if(!(value instanceof Set<?>) && !(value instanceof List<?>)) {
                        detalleGuardar += "<br>";
                        if (value != null) {
                            detalleGuardar += field.getName() + ": " + value.toString();
                        } else {
                            detalleGuardar += field.getName() + ": " + "null";
                        }
                    }
                }
            }
            if(oldEntity != null) {
                detalleGuardar += "<br><br><b>Entidad anterior:</b>";
                Object someObject = oldEntity;
                for (Field field : someObject.getClass().getDeclaredFields()) {
                    field.setAccessible(true); // You might want to set modifier to public first.
                    Object value = field.get(someObject);
                    if(!(value instanceof Set<?>) && !(value instanceof List<?>)) {
                        detalleGuardar += "<br>";
                        if (value != null) {
                            detalleGuardar += field.getName() + ": " + value.toString();
                        } else {
                            detalleGuardar += field.getName() + ": " + "null";
                        }
                    }
                }
            }
            bitacora.setDetalle(detalleGuardar);
            if(tipo == null){
                tipo = 1;
            }
            bitacora.setTipo(tipo);
            bitacora.setFecha(ZonedDateTime.now());

            bitacora = bitacoraRepository.save(bitacora);
        } catch (Exception ex) {
            log.error("Error saving bitacora : {}", ex);
        }
    }

    /**
     * GET  /bitacoras : get all the bitacoras.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bitacoras in body
     */
    @GetMapping("/bitacoras")
    public ResponseEntity<List<Bitacora>> getAllBitacoras(Pageable pageable) {
        log.debug("REST request to get a page of Bitacoras");
        Page<Bitacora> page = bitacoraRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bitacoras");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /bitacoras/:id : get the "id" bitacora.
     *
     * @param id the id of the bitacora to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bitacora, or with status 404 (Not Found)
     */
    @GetMapping("/bitacoras/{id}")
    public ResponseEntity<Bitacora> getBitacora(@PathVariable Long id) {
        log.debug("REST request to get Bitacora : {}", id);
        Optional<Bitacora> bitacora = bitacoraRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bitacora);
    }
}
