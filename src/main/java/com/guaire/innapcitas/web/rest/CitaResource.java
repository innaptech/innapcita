package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.*;
import com.guaire.innapcitas.repository.*;
import com.guaire.innapcitas.security.AuthoritiesConstants;
import com.guaire.innapcitas.service.MailService;
import com.guaire.innapcitas.web.rest.errors.BadRequestAlertException;
import com.guaire.innapcitas.web.rest.util.HeaderUtil;
import com.guaire.innapcitas.web.rest.util.PaginationUtil;
import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang.CharEncoding;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * REST controller for managing Cita.
 */
@RestController
@RequestMapping("/api")
public class CitaResource {

    private final Logger log = LoggerFactory.getLogger(CitaResource.class);

    private static final String ENTITY_NAME = "cita";

    private final CitaRepository citaRepository;

    private final UserRepository userRepository;

    private final EstatusCitaRepository estatusCitaRepository;

    private final HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository;

    private final HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository;

    private final HistoriaPersonalNinoRepository historiaPersonalNinoRepository;

    private final HistoriaMedicaNinoRepository historiaMedicaNinoRepository;

    private final EmailCitaRepository emailCitaRepository;

    private final EvolucionPacienteRepository evolucionPacienteRepository;

    private final ExamenComplementarioRepository examenComplementarioRepository;

    private final BitacoraResource bitacoraResource;

    private MailService mailService;

    private JavaMailSenderImpl javaMailSender;

    private JHipsterProperties jHipsterProperties;

    public CitaResource(CitaRepository citaRepository, HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository,
                        HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository, HistoriaPersonalNinoRepository historiaPersonalNinoRepository,
                        HistoriaMedicaNinoRepository historiaMedicaNinoRepository, MailService mailService,
                        JavaMailSenderImpl javaMailSender, JHipsterProperties jHipsterProperties,
                        EstatusCitaRepository estatusCitaRepository, EmailCitaRepository emailCitaRepository, UserRepository userRepository,
                        EvolucionPacienteRepository evolucionPacienteRepository, ExamenComplementarioRepository examenComplementarioRepositor,
                        BitacoraResource bitacoraResource) {
        this.citaRepository = citaRepository;
        this.historiaMedicaAdultoRepository = historiaMedicaAdultoRepository;
        this.historiaMedicaNinoRepository = historiaMedicaNinoRepository;
        this.historiaPersonalAdultoRepository = historiaPersonalAdultoRepository;
        this.historiaPersonalNinoRepository = historiaPersonalNinoRepository;
        this.mailService = mailService;
        this.javaMailSender = javaMailSender;
        this.jHipsterProperties = jHipsterProperties;
        this.estatusCitaRepository = estatusCitaRepository;
        this.emailCitaRepository = emailCitaRepository;
        this.userRepository = userRepository;
        this.evolucionPacienteRepository = evolucionPacienteRepository;
        this.examenComplementarioRepository = examenComplementarioRepositor;
        this.bitacoraResource = bitacoraResource;
    }

    /**
     * POST  /citas : Create a new cita.
     *
     * @param cita the cita to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cita, or with status 400 (Bad Request) if the cita has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/citas")
    public ResponseEntity<Cita> createCita(@Valid @RequestBody Cita cita, Principal principal) throws URISyntaxException, MessagingException {
        log.debug("REST request to save Cita : {}", cita);
        bitacoraResource.newBitacora(principal.getName(), "Creando Cita", "Creando nueva Cita", cita, null, 1);

        if (cita.getId() != null) {
            throw new BadRequestAlertException("A new cita cannot already have an ID", ENTITY_NAME, "idexists");
        }

        try{
            cita.setEstatusCita(estatusCitaRepository.findById(2L).get());
        } catch (Exception ex) {
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error guardando el Estatus " + ex.toString(), cita, null, 2);
            log.error("Error setting status : {}", ex);
        }

        Cita result = null;
        try{
            result = citaRepository.save(cita);
        } catch (Exception ex) {
            log.error("Error saving Cita : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error guardando la Cita " + ex.toString(), cita, null, 2);
        }

        try {
            User paciente = userRepository.findOneByLogin(cita.getPaciente().getLogin()).get();
            paciente.setFirstName(cita.getPaciente().getFirstName());
            paciente.setLastName(cita.getPaciente().getLastName());
            paciente.setTelefono(cita.getPaciente().getTelefono());
            paciente.setNumeroHistoria(cita.getPaciente().getNumeroHistoria());
            paciente = userRepository.save(paciente);

            cita.setHistoriaPersonalAdultos(historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaAdultos(historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaPersonalNinos(historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaNinos(historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));
        } catch (Exception ex) {
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error guardando el Paciente " + ex.toString(), cita, null, 2);
            log.error("Error saving user : {}", ex);
        }

        try {
            //Crear historias
            if((cita.getHistoriaPersonalNinos().isEmpty()) && (cita.isPacienteNino() != null) && (cita.isPacienteNino() == true)){
                HistoriaPersonalNino historiaPersonalNino = new HistoriaPersonalNino();
                historiaPersonalNino.setEspecialidad(cita.getEspecialidad());
                historiaPersonalNino.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalNino.setPaciente(result.getPaciente());
                cita.getHistoriaPersonalNinos().add(historiaPersonalNinoRepository.save(historiaPersonalNino));
                HistoriaMedicaNino historiaMedicaNino = new HistoriaMedicaNino();
                historiaMedicaNino.setPaciente(result.getPaciente());
                cita.getHistoriaMedicaNinos().add(historiaMedicaNinoRepository.save(historiaMedicaNino));
            } else if (cita.getHistoriaPersonalAdultos().isEmpty()) {
                HistoriaPersonalAdulto historiaPersonalAdulto = new HistoriaPersonalAdulto();
                historiaPersonalAdulto.setEspecialidad(cita.getEspecialidad());
                historiaPersonalAdulto.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalAdulto.setPaciente(result.getPaciente());
                cita.getHistoriaPersonalAdultos().add(historiaPersonalAdultoRepository.save(historiaPersonalAdulto));
                HistoriaMedicaAdulto historiaMedicaAdulto = new HistoriaMedicaAdulto();
                historiaMedicaAdulto.setPaciente(result.getPaciente());
                cita.getHistoriaMedicaAdultos().add(historiaMedicaAdultoRepository.save(historiaMedicaAdulto));
            }
        } catch (Exception ex) {
            log.error("Error saving history : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error guardando nuevas Historias " + ex.toString(), cita, null, 2);
        }

        try {
            EvolucionPaciente evo = new EvolucionPaciente();
            evo.setCita(result);
            evolucionPacienteRepository.save(evo);
        } catch (Exception ex) {
            log.error("Error saving evolution : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error guardando la nueva Evolución del Paciente " + ex.toString(), cita, null, 2);
        }

        try {
            //Enviar al equipo
            String fechaConsulta = "Fecha: - ";
            if(result.getFecha() != null) {
                fechaConsulta = "Fecha: " + getFecha(result.getFecha());
            }

            String medicoTratante = "";
            if(result.getEspecialidadMedico() != null) {
                medicoTratante = "<li>Médico Tratante: " + result.getEspecialidadMedico().getMedico().getFirstName() + " " + result.getEspecialidadMedico().getMedico().getLastName() + "</li>";
            }

            String consultaOnline = "";
            if (result.getConsultaOnline() != null && result.getConsultaOnline()) {
                consultaOnline = "Sí";
            } else {
                consultaOnline = "No";
            }

            String tituloMensaje = "Nueva cita para " + result.getEspecialidad().getEspecialidad();
            String contenidoMensaje = "Buenas," +
                "<br/><br/>Se ha solicitado una nueva cita con los siguientes datos:<br/>" +
                "<ul>" +
                "<li>Usuario:  " + result.getPaciente().getEmail() + " </li>" +
                "<li>Especialidad: " + result.getEspecialidad().getEspecialidad() + "</li>" +
                medicoTratante +
                "<li>Tipo de consulta: " + result.getTipoConsulta().getNombre() + "</li>" +
                "<li>Cita Online: " + consultaOnline + "</li>" +
                "<li>" + fechaConsulta + "</li>" +
                "<li>Link: " + "<a href=\"http://innap.net/citas/?/cita/" + result.getId().toString() + "/view\" target=\"_blank\"><b>cita programada</b></a>" +
                "</ul>" +
                "<br/>Atentamente,<br/><br/><i>Equipo de Innap Citas</i>";

            String[] recipientes = result.getEspecialidad().getEmail().split(",");

            for(String recipienteMensaje : recipientes) {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
                message.setTo(recipienteMensaje);
                message.setFrom(jHipsterProperties.getMail().getFrom());
                message.setSubject(tituloMensaje);
                message.setText(contenidoMensaje, true);
                javaMailSender.send(mimeMessage);
            }

            //Enviar al paciente
            EmailCita emailCita = emailCitaRepository.findAll().get(0);

            String mensaje = emailCita.getMensaje();
            if(result.getEspecialidadMedico() != null) {
                mensaje = mensaje.replace("@nombreDoctor",result.getEspecialidadMedico().getMedico().getFirstName());
                mensaje = mensaje.replace("@apellidoDoctor",result.getEspecialidadMedico().getMedico().getLastName());
            } else {
                mensaje = mensaje.replace("@nombreDoctor","- por asignar -");
                mensaje = mensaje.replace("@apellidoDoctor","");
            }
            mensaje = mensaje.replace("@tipoConsulta",result.getTipoConsulta().getNombre());
            mensaje = mensaje.replace("@emailAdministrador",result.getEspecialidad().getEmail());
            if(result.getEspecialidad().getAdministrador() != null) {
                mensaje = mensaje.replace("@nombreAdministrador",result.getEspecialidad().getAdministrador());
            } else {
                mensaje = mensaje.replace("@nombreAdministrador", " ");
            }
            if(result.getFecha() != null) {
                mensaje = mensaje.replace("@fecha",fechaConsulta);
            } else {
                mensaje = mensaje.replace("@fecha","- por definir -");
            }
            mensaje = mensaje.replace("@especialidad",result.getEspecialidad().getEspecialidad());
            mensaje = mensaje.replace("@nombre",result.getPaciente().getFirstName());
            mensaje = mensaje.replace("@apellido",result.getPaciente().getLastName());
            mensaje = mensaje.replace("@email",result.getPaciente().getEmail());

            String consultaOnlineText = "";
            if (result.getConsultaOnline() != null && result.getConsultaOnline()) {
                consultaOnlineText = "Online";
            } else {
                consultaOnlineText = "Presencial";
            }
            mensaje = mensaje.replace("@consultaOnline",consultaOnlineText);

            mensaje = mensaje.replace("@nuevaCitaLink","<a href=\"http://innap.net/citas/?/cita/" + result.getId().toString() + "/view\" target=\"_blank\"><b>cita programada</b></a>");
            //emailCita.setMensaje(mensaje);

            String titulo = emailCita.getTitulo();
            if(result.getEspecialidadMedico() != null) {
                titulo = titulo.replace("@nombreDoctor",result.getEspecialidadMedico().getMedico().getFirstName());
                titulo = titulo.replace("@apellidoDoctor",result.getEspecialidadMedico().getMedico().getLastName());
            } else {
                titulo = titulo.replace("@nombreDoctor","- por asignar -");
                titulo = titulo.replace("@apellidoDoctor","");
            }
            titulo = titulo.replace("@tipoConsulta",result.getTipoConsulta().getNombre());
            titulo = titulo.replace("@emailAdministrador",result.getEspecialidad().getEmail());
            if(result.getEspecialidad().getAdministrador() != null) {
                titulo = titulo.replace("@nombreAdministrador",result.getEspecialidad().getAdministrador());
            } else {
                titulo = titulo.replace("@nombreAdministrador", " ");
            }
            if(result.getFecha() != null) {
                titulo = titulo.replace("@fecha",fechaConsulta);
            } else {
                titulo = titulo.replace("@fecha","- por definir -");
            }
            titulo = titulo.replace("@especialidad",result.getEspecialidad().getEspecialidad());
            titulo = titulo.replace("@nombre",result.getPaciente().getFirstName());
            titulo = titulo.replace("@apellido",result.getPaciente().getLastName());
            titulo = titulo.replace("@email",result.getPaciente().getEmail());

            //emailCita.setTitulo(titulo);
            //Enviar al Paciente
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

            message.setTo(result.getPaciente().getEmail());
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(titulo);
            message.setText(mensaje, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception ex) {
            log.error("Error sending email : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error guardando Cita", "Error enviando los Correos " + ex.toString(), cita, null, 2);
        }

        bitacoraResource.newBitacora(principal.getName(), "Cita Creada", "Cita nueva creada satisfactoriamente", cita, null, 1);

        return ResponseEntity.created(new URI("/api/citas/" + result.getId().toString()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /citas : Updates an existing cita.
     *
     * @param cita the cita to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cita,
     * or with status 400 (Bad Request) if the cita is not valid,
     * or with status 500 (Internal Server Error) if the cita couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Transactional
    @PutMapping("/citas")
    public ResponseEntity<Cita> updateCita(@Valid @RequestBody Cita cita, Principal principal) throws URISyntaxException, MessagingException {
        log.debug("REST request to update Cita : {}", cita);

        cita.setEvolucionPaciente(null);

        if (cita.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Cita citaAux = citaRepository.findById(cita.getId()).get();
        bitacoraResource.newBitacora(principal.getName(), "Editando Cita", "Editando una Cita existente ", cita, citaAux, 1);

        boolean fechaAgregada = false;
        if (cita.getFecha() != null && citaAux.getFecha() == null) {
            fechaAgregada = true;
        }

        boolean citaPagada = false;
        if (cita.getEstatusCita().getId() == 1L && citaAux.getEstatusCita().getId() != 1L) {
            citaPagada = true;
        }

        Cita result = null;
        try{
            result = citaRepository.save(cita);
        } catch (Exception ex) {
            log.error("Error saving Cita : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error editando Cita", "Error editando la Cita " + ex.toString(), cita, citaAux, 2);
        }

        try {
            User paciente = userRepository.getOne(cita.getPaciente().getId());
            paciente.setFirstName(cita.getPaciente().getFirstName());
            paciente.setLastName(cita.getPaciente().getLastName());
            paciente.setTelefono(cita.getPaciente().getTelefono());
            paciente.setNumeroHistoria(cita.getPaciente().getNumeroHistoria());
            userRepository.save(paciente);
        } catch (Exception ex) {
            log.error("Error sending email : {}", ex);
            bitacoraResource.newBitacora(principal.getName(), "Error editando Cita", "Error guardando el Paciente " + ex.toString(), cita, citaAux, 2);
        }

        if (fechaAgregada || citaPagada) {
            try {
                //Enviar al doctor
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

                String fechaConsulta = "Fecha: -";
                if(result.getFecha() != null) {
                    fechaConsulta = "Fecha: ";
                    fechaConsulta += getFecha(result.getFecha());
                }

                String medicoTratante = "";
                if(result.getEspecialidadMedico() != null) {
                    medicoTratante = "<li>Médico Tratante: " + result.getEspecialidadMedico().getMedico().getFirstName() + " " + result.getEspecialidadMedico().getMedico().getLastName() + "</li>";
                }

                String consultaOnline = "";
                if (result.getConsultaOnline() != null && result.getConsultaOnline()) {
                    consultaOnline = "Sí";
                } else {
                    consultaOnline = "No";
                }

                message.setTo(result.getEspecialidadMedico().getMedico().getEmail());
                message.setFrom(jHipsterProperties.getMail().getFrom());
                message.setSubject("Nueva cita para " + result.getEspecialidad().getEspecialidad());
                message.setText("Buenas," +
                    "<br/><br/>Se le ha asignado una nueva cita con los siguientes datos:<br/>" +
                    "<ul>" +
                    "<li>Usuario:  " + result.getPaciente().getEmail() + " </li>" +
                    "<li>Especialidad: " + result.getEspecialidad().getEspecialidad() + "</li>" +
                    medicoTratante +
                    "<li>Tipo de consulta: " + result.getTipoConsulta().getNombre() + "</li>" +
                    "<li>Cita Online: " + consultaOnline + "</li>" +
                    "<li>" + fechaConsulta + "</li>" +
                    "<li>Link: " + "<a href=\"http://innap.net/citas/?/cita/" + result.getId().toString() + "/view\" target=\"_blank\"><b>cita programada</b></a>" +
                    "</ul>" +
                    "<br/>Atentamente,<br/><br/><i>Equipo de Innap Citas</i>", true);

                javaMailSender.send(mimeMessage);

                EmailCita emailCita = emailCitaRepository.findAll().get(0);

                String mensaje = emailCita.getMensaje();
                if(result.getEspecialidadMedico() != null) {
                    mensaje = mensaje.replace("@nombreDoctor",result.getEspecialidadMedico().getMedico().getFirstName());
                    mensaje = mensaje.replace("@apellidoDoctor",result.getEspecialidadMedico().getMedico().getLastName());
                } else {
                    mensaje = mensaje.replace("@nombreDoctor","- por asignar -");
                    mensaje = mensaje.replace("@apellidoDoctor","");
                }
                mensaje = mensaje.replace("@tipoConsulta",result.getTipoConsulta().getNombre());
                mensaje = mensaje.replace("@emailAdministrador",result.getEspecialidad().getEmail());
                if(result.getEspecialidad().getAdministrador() != null) {
                    mensaje = mensaje.replace("@nombreAdministrador",result.getEspecialidad().getAdministrador());
                } else {
                    mensaje = mensaje.replace("@nombreAdministrador", " ");
                }
                if(result.getFecha() != null) {
                    mensaje = mensaje.replace("@fecha",fechaConsulta);
                } else {
                    mensaje = mensaje.replace("@fecha","- por definir -");
                }
                mensaje = mensaje.replace("@especialidad",result.getEspecialidad().getEspecialidad());
                mensaje = mensaje.replace("@nombre",result.getPaciente().getFirstName());
                mensaje = mensaje.replace("@apellido",result.getPaciente().getLastName());
                mensaje = mensaje.replace("@email",result.getPaciente().getEmail());

                String consultaOnlineText = "";
                if (result.getConsultaOnline() != null && result.getConsultaOnline()) {
                    consultaOnlineText = "Online";
                } else {
                    consultaOnlineText = "Presencial";
                }
                mensaje = mensaje.replace("@consultaOnline",consultaOnlineText);

                mensaje = mensaje.replace("@nuevaCitaLink","<a href=\"http://innap.net/citas/?/cita/" + result.getId().toString() + "/view\" target=\"_blank\"><b>cita programada</b></a>");
                //emailCita.setMensaje(mensaje);

                String titulo = emailCita.getTitulo();
                if(result.getEspecialidadMedico() != null) {
                    titulo = titulo.replace("@nombreDoctor",result.getEspecialidadMedico().getMedico().getFirstName());
                    titulo = titulo.replace("@apellidoDoctor",result.getEspecialidadMedico().getMedico().getLastName());
                } else {
                    titulo = titulo.replace("@nombreDoctor","- por asignar -");
                    titulo = titulo.replace("@apellidoDoctor","");
                }
                titulo = titulo.replace("@tipoConsulta",result.getTipoConsulta().getNombre());
                titulo = titulo.replace("@emailAdministrador",result.getEspecialidad().getEmail());
                if(result.getEspecialidad().getAdministrador() != null) {
                    titulo = titulo.replace("@nombreAdministrador",result.getEspecialidad().getAdministrador());
                } else {
                    titulo = titulo.replace("@nombreAdministrador", " ");
                }
                if(result.getFecha() != null) {
                    titulo = titulo.replace("@fecha",fechaConsulta);
                } else {
                    titulo = titulo.replace("@fecha","- por definir -");
                }
                titulo = titulo.replace("@especialidad",result.getEspecialidad().getEspecialidad());
                titulo = titulo.replace("@nombre",result.getPaciente().getFirstName());
                titulo = titulo.replace("@apellido",result.getPaciente().getLastName());
                titulo = titulo.replace("@email",result.getPaciente().getEmail());

                //emailCita.setTitulo(titulo);
                //Enviar al Paciente
                mimeMessage = javaMailSender.createMimeMessage();

                message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

                message.setTo(result.getPaciente().getEmail());
                message.setFrom(jHipsterProperties.getMail().getFrom());
                message.setSubject(titulo);
                message.setText(mensaje, true);

                javaMailSender.send(mimeMessage);
            } catch (Exception ex) {
                log.error("Error sending email : {}", ex);
                bitacoraResource.newBitacora(principal.getName(), "Error editando Cita", "Error enviando el Correo " + ex.toString(), cita, citaAux, 2);
            }
        }

        bitacoraResource.newBitacora(principal.getName(), "Cita Editada", "Cita Editada exitosamente ", cita, null, 1);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cita.getId().toString()))
            .body(result);
    }

    /**
     * GET  /citas : get all the citas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of citas in body
     */
    @GetMapping("/citas")
    public ResponseEntity<List<Cita>> getAllCitas(Pageable pageable) {
        log.debug("REST request to get a page of Citas");
        Page<Cita> page = citaRepository.findAll(pageable);

        for(Cita cita : page.getContent()){

            if(cita.getEstatusCita() == null) {
                cita.setEstatusCita(estatusCitaRepository.getOne(2L));
            }

            cita.setHistoriaPersonalAdultos(historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaAdultos(historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaPersonalNinos(historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaNinos(historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));

            //Crear historias
            if((cita.getHistoriaPersonalNinos().isEmpty()) && (cita.isPacienteNino() != null) && (cita.isPacienteNino() == true)){
                HistoriaPersonalNino historiaPersonalNino = new HistoriaPersonalNino();
                historiaPersonalNino.setEspecialidad(cita.getEspecialidad());
                historiaPersonalNino.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalNino.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalNinos().add(historiaPersonalNinoRepository.save(historiaPersonalNino));
                HistoriaMedicaNino historiaMedicaNino = new HistoriaMedicaNino();
                historiaMedicaNino.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaNinos().add(historiaMedicaNinoRepository.save(historiaMedicaNino));
            } else if (cita.getHistoriaPersonalAdultos().isEmpty()) {
                HistoriaPersonalAdulto historiaPersonalAdulto = new HistoriaPersonalAdulto();
                historiaPersonalAdulto.setEspecialidad(cita.getEspecialidad());
                historiaPersonalAdulto.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalAdultos().add(historiaPersonalAdultoRepository.save(historiaPersonalAdulto));
                HistoriaMedicaAdulto historiaMedicaAdulto = new HistoriaMedicaAdulto();
                historiaMedicaAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaAdultos().add(historiaMedicaAdultoRepository.save(historiaMedicaAdulto));
            }

            cita.setEvolucionPaciente(evolucionPacienteRepository.findByCita_Id(cita.getId()));
            if (cita.getEvolucionPaciente() == null) {
                EvolucionPaciente evo = new EvolucionPaciente();
                evo.setCita(cita);
                evolucionPacienteRepository.save(evo);
            }
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/citas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /citas : get all the citas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of citas in body
     */
    @GetMapping("/citas/user")
    public ResponseEntity<List<Cita>> getAllUserCitas(Pageable pageable, Principal principal) {
        log.debug("REST request to get a page of Citas");

        Page<Cita> page;
        User user = userRepository.findOneWithAuthoritiesByLogin(principal.getName()).get();

        boolean isAdmin = false;
        boolean isMedico =  false;
        boolean isRecepcion =  false;
        for(Authority auth : user.getAuthorities()){
            if(auth.getName().equals(AuthoritiesConstants.ADMIN))
                isAdmin = true;
            if(auth.getName().equals(AuthoritiesConstants.MEDICO))
                isMedico = true;
            if(auth.getName().equals(AuthoritiesConstants.RECEPCION))
                isRecepcion = true;
        }

        if (isAdmin || isRecepcion) {
            page = citaRepository.findAll(pageable);
        } else if (isMedico) {
            page = citaRepository.findAllByEspecialidadMedico_Medico_Login(user.getLogin(), pageable);
        } else {
            page = citaRepository.findAllByPaciente_Login(user.getLogin(), pageable);
        }

        for(Cita cita : page.getContent()){
            cita.setHistoriaPersonalAdultos(historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaAdultos(historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaPersonalNinos(historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaNinos(historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));

            //Crear historias
            if((cita.getHistoriaPersonalNinos().isEmpty()) && (cita.isPacienteNino() != null) && (cita.isPacienteNino() == true)){
                HistoriaPersonalNino historiaPersonalNino = new HistoriaPersonalNino();
                historiaPersonalNino.setEspecialidad(cita.getEspecialidad());
                historiaPersonalNino.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalNino.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalNinos().add(historiaPersonalNinoRepository.save(historiaPersonalNino));
                HistoriaMedicaNino historiaMedicaNino = new HistoriaMedicaNino();
                historiaMedicaNino.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaNinos().add(historiaMedicaNinoRepository.save(historiaMedicaNino));
            } else if (cita.getHistoriaPersonalAdultos().isEmpty()) {
                HistoriaPersonalAdulto historiaPersonalAdulto = new HistoriaPersonalAdulto();
                historiaPersonalAdulto.setEspecialidad(cita.getEspecialidad());
                historiaPersonalAdulto.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalAdultos().add(historiaPersonalAdultoRepository.save(historiaPersonalAdulto));
                HistoriaMedicaAdulto historiaMedicaAdulto = new HistoriaMedicaAdulto();
                historiaMedicaAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaAdultos().add(historiaMedicaAdultoRepository.save(historiaMedicaAdulto));
            }

            cita.setEvolucionPaciente(evolucionPacienteRepository.findByCita_Id(cita.getId()));
            if (cita.getEvolucionPaciente() == null) {
                EvolucionPaciente evo = new EvolucionPaciente();
                evo.setCita(cita);
                evolucionPacienteRepository.save(evo);
            }
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/citas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /citas/:id : get the "id" cita.
     *
     * @param id the id of the cita to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cita, or with status 404 (Not Found)
     */
    @GetMapping("/citas/{id}")
    public ResponseEntity<Cita> getCita(@PathVariable Long id) {
        log.debug("REST request to get Cita : {}", id);
        Optional<Cita> cita = citaRepository.findById(id);

        cita.get().setHistoriaPersonalAdultos(historiaPersonalAdultoRepository.findByPaciente_Id(cita.get().getPaciente().getId()));
        cita.get().setHistoriaMedicaAdultos(historiaMedicaAdultoRepository.findByPaciente_Id(cita.get().getPaciente().getId()));
        cita.get().setHistoriaPersonalNinos(historiaPersonalNinoRepository.findByPaciente_Id(cita.get().getPaciente().getId()));
        cita.get().setHistoriaMedicaNinos(historiaMedicaNinoRepository.findByPaciente_Id(cita.get().getPaciente().getId()));

        //Crear historias
        if((cita.get().getHistoriaPersonalNinos().isEmpty()) && (cita.get().isPacienteNino() != null) && (cita.get().isPacienteNino() == true)){
            HistoriaPersonalNino historiaPersonalNino = new HistoriaPersonalNino();
            historiaPersonalNino.setEspecialidad(cita.get().getEspecialidad());
            historiaPersonalNino.setTipoConsulta(cita.get().getTipoConsulta());
            historiaPersonalNino.setPaciente(cita.get().getPaciente());
            cita.get().getHistoriaPersonalNinos().add(historiaPersonalNinoRepository.save(historiaPersonalNino));
            HistoriaMedicaNino historiaMedicaNino = new HistoriaMedicaNino();
            historiaMedicaNino.setPaciente(cita.get().getPaciente());
            cita.get().getHistoriaMedicaNinos().add(historiaMedicaNinoRepository.save(historiaMedicaNino));
        } else if (cita.get().getHistoriaPersonalAdultos().isEmpty()) {
            HistoriaPersonalAdulto historiaPersonalAdulto = new HistoriaPersonalAdulto();
            historiaPersonalAdulto.setEspecialidad(cita.get().getEspecialidad());
            historiaPersonalAdulto.setTipoConsulta(cita.get().getTipoConsulta());
            historiaPersonalAdulto.setPaciente(cita.get().getPaciente());
            cita.get().getHistoriaPersonalAdultos().add(historiaPersonalAdultoRepository.save(historiaPersonalAdulto));
            HistoriaMedicaAdulto historiaMedicaAdulto = new HistoriaMedicaAdulto();
            historiaMedicaAdulto.setPaciente(cita.get().getPaciente());
            cita.get().getHistoriaMedicaAdultos().add(historiaMedicaAdultoRepository.save(historiaMedicaAdulto));
        }

        cita.get().setEvolucionPaciente(evolucionPacienteRepository.findByCita_Id(cita.get().getId()));
        if (cita.get().getEvolucionPaciente() == null) {
            EvolucionPaciente evo = new EvolucionPaciente();
            evo.setCita(cita.get());
            evolucionPacienteRepository.save(evo);
        }

        return ResponseUtil.wrapOrNotFound(cita);
    }

    /**
     * GET  /citas : get all the citas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of citas in body
     */
    @GetMapping("/citas/search/{criteria}")
    public ResponseEntity<List<Cita>> searchCitas(@PathVariable(value = "criteria", required = false) String criteria, Pageable pageable, Principal principal) {
        log.debug("REST request to get a page of Citas");

        User user = userRepository.findOneWithAuthoritiesByLogin(principal.getName()).get();

        Long id = 0L;
        try{
            id = Long.parseLong(criteria);
        } catch (Exception ex) {

        }

        //advanced search parameters init
        String[] splitted = criteria.split("\\+");
        if(splitted.length > 1) {
            criteria = splitted[0];
        }

        boolean isAdmin = false;
        boolean isMedico =  false;
        boolean isRecepcion =  false;
        for(Authority auth : user.getAuthorities()){
            if(auth.getName().equals(AuthoritiesConstants.ADMIN))
                isAdmin = true;
            if(auth.getName().equals(AuthoritiesConstants.MEDICO))
                isMedico = true;
            if(auth.getName().equals(AuthoritiesConstants.RECEPCION))
                isRecepcion = true;
        }

        String nombre = "";
        String apellido = "";
        String[] nombrap = criteria.split(" ");
        if (nombrap.length > 1) {
            nombre = nombrap[0];
            apellido = nombrap[1];
        } else {
            nombre = criteria;
            apellido = criteria;
        }

        List<Cita> allCitas =  new ArrayList<>();

        if(!criteria.startsWith("+")) {
            if (isAdmin || isRecepcion) {
                allCitas = citaRepository.findAllByIdOrPaciente_LoginOrPaciente_FirstNameContainsIgnoreCaseOrPaciente_LastNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrEspecialidadMedico_DescripcionContainsIgnoreCaseOrEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(id, criteria, nombre, apellido, criteria, criteria, criteria, criteria, criteria);
            } else if (isMedico) {
                allCitas = citaRepository.findAllByIdOrPaciente_LoginOrPaciente_FirstNameContainsIgnoreCaseOrPaciente_LastNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrEspecialidadMedico_DescripcionContainsIgnoreCaseOrEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(id, criteria, nombre, apellido, criteria, criteria, criteria, criteria, criteria);
            } else {
                allCitas = citaRepository.findAllByPaciente_LoginAndEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_DescripcionContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(user.getLogin(), criteria, user.getLogin(),criteria, user.getLogin(),criteria, user.getLogin(),criteria, user.getLogin(),criteria);
            }
        }


        //Sub-filters
        List<Long> citasIDs = getIds(allCitas);
        Page<Cita> page = null;
        if(splitted.length == 1 && !criteria.contains("+")) {
            if( citasIDs.size() > 0) {
                page = citaRepository.findCitasPaged(citasIDs, pageable);
            } else{
                page = new PageImpl<>(allCitas);
            }
        } else {
            if(citasIDs.size() == 0) {
                allCitas = citaRepository.findAll();
                citasIDs = getIds(allCitas);
            }

            //advanced search parameters search
            String[] statusCita = new String[5];
            String[] tipoCita = new String[2];
            String[] meses = new String[12];

            meses[0] = "enero";
            meses[1] = "febrero";
            meses[2] = "marzo";
            meses[3] = "abril";
            meses[4] = "mayo";
            meses[5] = "junio";
            meses[6] = "julio";
            meses[7] = "agosto";
            meses[8] = "septiembre";
            meses[9] = "octubre";
            meses[10] = "noviembre";
            meses[11] = "diciembre";

            tipoCita[0] = "Online";
            tipoCita[1] = "Presencial";

            statusCita[0] = "Pagada";
            statusCita[1] = "Por Pagar";
            statusCita[2] = "Cancelada";
            statusCita[3] = "Exonerada";
            statusCita[4] = "Cita Finalizada";

            int ij = 0;
            for(String advancedCriteria : splitted) {

                if(ij > 0) {

                    //busqueda por rango de fechas
                    final ZoneId zoneId = ZoneId.systemDefault();
                    Date startDate = null;
                    Date endDate = null;
                    String dateTester = advancedCriteria;
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    if(dateTester.contains("*")) {
                        String[] dateTesterArray = dateTester.split("\\*");
                        if (dateTesterArray.length >= 2) {
                            sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                startDate = sdf1.parse(dateTesterArray[0]);
                                endDate = sdf1.parse(dateTesterArray[1]);
                                endDate.setDate(endDate.getDate() + 1);
                                citasIDs = getIds(allCitas);
                                allCitas = citaRepository.findCitasFilteredByMonth(ZonedDateTime.ofInstant(startDate.toInstant(), zoneId) , ZonedDateTime.ofInstant(endDate.toInstant(), zoneId), citasIDs);
                            } catch (ParseException e) {
                                //e.printStackTrace();
                                startDate = null;
                                endDate = null;
                            }
                        }
                    } else {
                        //busqueda por fecha
                        startDate = null;
                        endDate = null;
                        dateTester = advancedCriteria;
                        try {
                            startDate = sdf1.parse(dateTester);
                            endDate = sdf1.parse(dateTester);
                            endDate.setDate(endDate.getDate() + 1);
                            citasIDs = getIds(allCitas);
                            allCitas = citaRepository.findCitasFilteredByMonth(ZonedDateTime.ofInstant(startDate.toInstant(), zoneId) , ZonedDateTime.ofInstant(endDate.toInstant(), zoneId), citasIDs);
                        } catch (ParseException e) {
                            //e.printStackTrace();
                            startDate = null;
                            endDate = null;
                        }
                    }

                    //busqueda por mes
                    if(allCitas.size() > 0) {
                        for (int i = 0; i < meses.length; i++) {
                            String mes = meses[i];
                            if (mes.equalsIgnoreCase(advancedCriteria.toLowerCase())) {
                                startDate = new Date();
                                startDate.setMonth(i);
                                startDate.setDate(0);
                                endDate = new Date();
                                endDate.setMonth(i);
                                endDate.setDate(30);

                                if (startDate.after(Date.from(ZonedDateTime.now().toInstant()))) {
                                    startDate.setYear(startDate.getYear() - 1);
                                    endDate.setYear(endDate.getYear() - 1);
                                }

                                citasIDs = getIds(allCitas);
                                allCitas = citaRepository.findCitasFilteredByMonth(ZonedDateTime.ofInstant(startDate.toInstant(), zoneId), ZonedDateTime.ofInstant(endDate.toInstant(), zoneId), citasIDs);
                            }
                        }
                    }

                    if(allCitas.size() > 0) {
                        for (String estatus : statusCita) {
                            if (estatus.equalsIgnoreCase(advancedCriteria)) {
                                citasIDs = getIds(allCitas);
                                allCitas = citaRepository.findCitasFilteredByStatus(estatus, citasIDs);
                            }
                        }
                    }

                    if(allCitas.size() > 0) {
                        for (String tipo : tipoCita) {
                            if (tipo.equalsIgnoreCase(advancedCriteria)) {
                                citasIDs = getIds(allCitas);
                                Boolean online = true;
                                if(tipo.equalsIgnoreCase("Presencial"))
                                    allCitas = citaRepository.findCitasFilteredByPresencial(citasIDs);
                                else
                                    allCitas = citaRepository.findCitasFilteredByOnline(online, citasIDs);
                            }
                        }
                    }

                    //page results
                    if(allCitas.size() > 0) {
                        citasIDs = getIds(allCitas);
                        page = citaRepository.findCitasPaged(citasIDs, pageable);
                    } else {
                        page = new PageImpl<>(allCitas);
                    }
                }
                ij = ij + 1;
            }
        }

        if(page == null) {
            page = new PageImpl<>(allCitas);
        }

        for(Cita cita : page.getContent()){
            cita.setHistoriaPersonalAdultos(historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaAdultos(historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaPersonalNinos(historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));
            cita.setHistoriaMedicaNinos(historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId()));

            //Crear historias
            if((cita.getHistoriaPersonalNinos().isEmpty()) && (cita.isPacienteNino() != null) && (cita.isPacienteNino() == true)){
                HistoriaPersonalNino historiaPersonalNino = new HistoriaPersonalNino();
                historiaPersonalNino.setEspecialidad(cita.getEspecialidad());
                historiaPersonalNino.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalNino.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalNinos().add(historiaPersonalNinoRepository.save(historiaPersonalNino));
                HistoriaMedicaNino historiaMedicaNino = new HistoriaMedicaNino();
                historiaMedicaNino.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaNinos().add(historiaMedicaNinoRepository.save(historiaMedicaNino));
            } else if (cita.getHistoriaPersonalAdultos().isEmpty()) {
                HistoriaPersonalAdulto historiaPersonalAdulto = new HistoriaPersonalAdulto();
                historiaPersonalAdulto.setEspecialidad(cita.getEspecialidad());
                historiaPersonalAdulto.setTipoConsulta(cita.getTipoConsulta());
                historiaPersonalAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaPersonalAdultos().add(historiaPersonalAdultoRepository.save(historiaPersonalAdulto));
                HistoriaMedicaAdulto historiaMedicaAdulto = new HistoriaMedicaAdulto();
                historiaMedicaAdulto.setPaciente(cita.getPaciente());
                cita.getHistoriaMedicaAdultos().add(historiaMedicaAdultoRepository.save(historiaMedicaAdulto));
            }

            cita.setEvolucionPaciente(evolucionPacienteRepository.findByCita_Id(cita.getId()));
            if (cita.getEvolucionPaciente() == null) {
                EvolucionPaciente evo = new EvolucionPaciente();
                evo.setCita(cita);
                evolucionPacienteRepository.save(evo);
            }
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/citas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    List<Long> getIds(List<Cita> citas) {
        List<Long> citasIds = new ArrayList<>();

        for (Cita cita : citas) {
            citasIds.add(cita.getId());
        }

        return citasIds;
    }

    /**
     * DELETE  /citas/:id : delete the "id" cita.
     *
     * @param id the id of the cita to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @Transactional
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        log.debug("REST request to delete Cita : {}", id);

        Cita cita = citaRepository.getOne(id);
        EvolucionPaciente evolucionPaciente = evolucionPacienteRepository.findByCita_Id(cita.getId());

        List<ExamenComplementario> examenes = examenComplementarioRepository.findByEvolucionPaciente_Id(evolucionPaciente.getId());
        for (ExamenComplementario ex : examenes) {
            examenComplementarioRepository.delete(ex);
        }
        evolucionPacienteRepository.delete(evolucionPaciente);
        citaRepository.delete(cita);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    String getFecha(LocalDateTime localDate) {
        String fecha = "";

        String[] meses = {"Ene.","Feb.","Mar.","Abr.","May.","Jun.","Jul.","Ago.","Sep.","Oct.","Nov.","Dic."};

        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMes = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterAno = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm a");
        fecha += localDate.minusHours(4L).format(formatterDia);
        fecha += " " + meses[Integer.parseInt(localDate.minusHours(4L).format(formatterMes)) - 1];
        fecha += " " + localDate.minusHours(4L).format(formatterAno);
        fecha += " " + localDate.minusHours(4L).format(formatterHour);

        return fecha;
    }

    String getFecha(LocalDate localDate) {
        String fecha = "";

        String[] meses = {"Ene.","Feb.","Mar.","Abr.","May.","Jun.","Jul.","Ago.","Sep.","Oct.","Nov.","Dic."};

        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMes = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterAno = DateTimeFormatter.ofPattern("yyyy");
        fecha += localDate.format(formatterDia);
        fecha += " " + meses[Integer.parseInt(localDate.format(formatterMes)) - 1];
        fecha += " " + localDate.format(formatterAno);

        return fecha;
    }

    String getFecha(ZonedDateTime localDate) {
        String fecha = "";

        String[] meses = {"Ene.","Feb.","Mar.","Abr.","May.","Jun.","Jul.","Ago.","Sep.","Oct.","Nov.","Dic."};

        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMes = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterAno = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm a");
        fecha += localDate.minusHours(4L).format(formatterDia);
        fecha += " " + meses[Integer.parseInt(localDate.minusHours(4L).format(formatterMes)) - 1];
        fecha += " " + localDate.minusHours(4L).format(formatterAno);
        fecha += " " + localDate.minusHours(4L).format(formatterHour);

        return fecha;
    }
}
