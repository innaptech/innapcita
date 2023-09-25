package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.HorarioDisponibilidad;
import com.guaire.innapcitas.domain.RangoHoras;
import com.guaire.innapcitas.repository.HorarioDisponibilidadRepository;
import com.guaire.innapcitas.repository.RangoHorasRepository;
import com.guaire.innapcitas.security.SecurityUtils;
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
 * REST controller for managing HorarioDisponibilidad.
 */
@RestController
@RequestMapping("/api")
public class HorarioDisponibilidadResource {

    private final Logger log = LoggerFactory.getLogger(HorarioDisponibilidadResource.class);

    private static final String ENTITY_NAME = "horarioDisponibilidad";

    private final HorarioDisponibilidadRepository horarioDisponibilidadRepository;

    private final RangoHorasRepository rangoHorasRepository;

    public HorarioDisponibilidadResource(HorarioDisponibilidadRepository horarioDisponibilidadRepository, RangoHorasRepository rangoHorasRepository) {
        this.horarioDisponibilidadRepository = horarioDisponibilidadRepository;
        this.rangoHorasRepository = rangoHorasRepository;
    }

    /**
     * POST  /horario-disponibilidads : Create a new horarioDisponibilidad.
     *
     * @param horarioDisponibilidad the horarioDisponibilidad to create
     * @return the ResponseEntity with status 201 (Created) and with body the new horarioDisponibilidad, or with status 400 (Bad Request) if the horarioDisponibilidad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/horario-disponibilidads")
    public ResponseEntity<HorarioDisponibilidad> createHorarioDisponibilidad(@Valid @RequestBody HorarioDisponibilidad horarioDisponibilidad) throws URISyntaxException {
        log.debug("REST request to save HorarioDisponibilidad : {}", horarioDisponibilidad);
        if (horarioDisponibilidad.getId() != null) {
            throw new BadRequestAlertException("A new horarioDisponibilidad cannot already have an ID", ENTITY_NAME, "idexists");
        }

        HorarioDisponibilidad result = horarioDisponibilidadRepository.save(horarioDisponibilidad);

        for(RangoHoras rangoHoras : horarioDisponibilidad.getHoras()){
            rangoHoras.setHorarioDisponibilidad(result);
            rangoHorasRepository.save(rangoHoras);
            rangoHoras.setHorarioDisponibilidad(null);
        }

        return ResponseEntity.created(new URI("/api/horario-disponibilidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /horario-disponibilidads : Updates an existing horarioDisponibilidad.
     *
     * @param horarioDisponibilidad the horarioDisponibilidad to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated horarioDisponibilidad,
     * or with status 400 (Bad Request) if the horarioDisponibilidad is not valid,
     * or with status 500 (Internal Server Error) if the horarioDisponibilidad couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/horario-disponibilidads")
    public ResponseEntity<HorarioDisponibilidad> updateHorarioDisponibilidad(@Valid @RequestBody HorarioDisponibilidad horarioDisponibilidad) throws URISyntaxException {
        log.debug("REST request to update HorarioDisponibilidad : {}", horarioDisponibilidad);
        if (horarioDisponibilidad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HorarioDisponibilidad result = horarioDisponibilidadRepository.save(horarioDisponibilidad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, horarioDisponibilidad.getId().toString()))
            .body(result);
    }

    /**
     * GET  /horario-disponibilidads : get all the horarioDisponibilidads.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of horarioDisponibilidads in body
     */
    @GetMapping("/horario-disponibilidads")
    public ResponseEntity<List<HorarioDisponibilidad>> getAllHorarioDisponibilidads(Pageable pageable) {
        log.debug("REST request to get a page of HorarioDisponibilidads");
        Page<HorarioDisponibilidad> page = horarioDisponibilidadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/horario-disponibilidads");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /horario-disponibilidads : get all the horarioDisponibilidads.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of horarioDisponibilidads in body
     */
    @GetMapping("/horario-disponibilidads/medico")
    public ResponseEntity<List<HorarioDisponibilidad>> getUserHorarioDisponibilidads(Pageable pageable) {
        log.debug("REST request to get a page of HorarioDisponibilidads");
        Page<HorarioDisponibilidad> page = horarioDisponibilidadRepository.findByEspecialidadMedico_Medico_LoginLikeIgnoreCase(
            SecurityUtils.getCurrentUserLogin().get().toString(), pageable); //SecurityUtils.getCurrentUserLogin(),

        //llenar dias

        for(HorarioDisponibilidad horario : page.getContent()){
            horario.setHoras(rangoHorasRepository.findByHorarioDisponibilidad_Id(horario.getId()));
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/horario-disponibilidads");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /horario-disponibilidads/:id : get the "id" horarioDisponibilidad.
     *
     * @param id the id of the horarioDisponibilidad to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the horarioDisponibilidad, or with status 404 (Not Found)
     */
    @GetMapping("/horario-disponibilidads/{id}")
    public ResponseEntity<HorarioDisponibilidad> getHorarioDisponibilidad(@PathVariable Long id) {
        log.debug("REST request to get HorarioDisponibilidad : {}", id);
        Optional<HorarioDisponibilidad> horarioDisponibilidad = horarioDisponibilidadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(horarioDisponibilidad);
    }

    /**
     * DELETE  /horario-disponibilidads/:id : delete the "id" horarioDisponibilidad.
     *
     * @param id the id of the horarioDisponibilidad to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/horario-disponibilidads/{id}")
    public ResponseEntity<Void> deleteHorarioDisponibilidad(@PathVariable Long id) {
        log.debug("REST request to delete HorarioDisponibilidad : {}", id);
        horarioDisponibilidadRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
