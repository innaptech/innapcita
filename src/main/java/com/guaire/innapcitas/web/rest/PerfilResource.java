package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.Perfil;
import com.guaire.innapcitas.domain.User;
import com.guaire.innapcitas.repository.PerfilRepository;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Perfil.
 */
@RestController
@RequestMapping("/api")
public class PerfilResource {

    private final Logger log = LoggerFactory.getLogger(PerfilResource.class);

    private static final String ENTITY_NAME = "perfil";

    private final PerfilRepository perfilRepository;

    private final UserRepository userRepository;

    public PerfilResource(PerfilRepository perfilRepository, UserRepository userRepository) {
        this.perfilRepository = perfilRepository;
        this.userRepository = userRepository;
    }

    /**
     * POST  /perfils : Create a new perfil.
     *
     * @param perfil the perfil to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perfil, or with status 400 (Bad Request) if the perfil has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/perfils")
    public ResponseEntity<Perfil> createPerfil(@RequestBody Perfil perfil) throws Exception {
        log.debug("REST request to save Perfil : {}", perfil);

        Perfil perfilAux = perfilRepository.findByUser_Id(perfil.getUser().getId());
        if(perfilAux != null) {
            throw new Exception("User already has a Perfil, please edit the existing one");
        }

        if (perfil.getId() != null) {
            throw new BadRequestAlertException("A new perfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Perfil result = perfilRepository.save(perfil);

        User user = result.getUser();
        if(result.getTitulo_abreviado() != null && !result.getTitulo_abreviado().equals("")) {
            user.setFirstName(result.getTitulo_abreviado() + " " + result.getNombre());
        } else {
            user.setFirstName(result.getNombre());
        }

        user.setLastName(result.getApellido());
        userRepository.save(user);

        return ResponseEntity.created(new URI("/api/perfils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /perfils : Updates an existing perfil.
     *
     * @param perfil the perfil to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perfil,
     * or with status 400 (Bad Request) if the perfil is not valid,
     * or with status 500 (Internal Server Error) if the perfil couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/perfils")
    public ResponseEntity<Perfil> updatePerfil(@RequestBody Perfil perfil) throws URISyntaxException {
        log.debug("REST request to update Perfil : {}", perfil);
        if (perfil.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Perfil result = perfilRepository.save(perfil);

        User user = result.getUser();
        if(result.getTitulo_abreviado() != null && !result.getTitulo_abreviado().equals("")) {
            user.setFirstName(result.getTitulo_abreviado() + " " + result.getNombre());
        } else {
            user.setFirstName(result.getNombre());
        }

        user.setLastName(result.getApellido());
        userRepository.save(user);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, perfil.getId().toString()))
            .body(result);
    }

    /**
     * GET  /perfils : get all the perfils.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of perfils in body
     */
    @GetMapping("/perfils")
    public ResponseEntity<List<Perfil>> getAllPerfils(Pageable pageable) {
        log.debug("REST request to get a page of Perfils");
        Page<Perfil> page = perfilRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/perfils");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /perfils/:id : get the "id" perfil.
     *
     * @param id the id of the perfil to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perfil, or with status 404 (Not Found)
     */
    @GetMapping("/perfils/{id}")
    public ResponseEntity<Perfil> getPerfil(@PathVariable Long id) {
        log.debug("REST request to get Perfil : {}", id);
        Optional<Perfil> perfil = perfilRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(perfil);
    }

    /**
     * DELETE  /perfils/:id : delete the "id" perfil.
     *
     * @param id the id of the perfil to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/perfils/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        log.debug("REST request to delete Perfil : {}", id);
        perfilRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
