package com.guaire.innapcitas.web.rest;

import com.google.gson.*;
import com.guaire.innapcitas.domain.*;
import com.guaire.innapcitas.repository.*;
import com.guaire.innapcitas.service.MailService;
import com.guaire.innapcitas.web.rest.errors.BadRequestAlertException;
import com.guaire.innapcitas.web.rest.util.HeaderUtil;
import com.guaire.innapcitas.web.rest.util.PaginationUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.List;

/**
 * REST controller for managing EvolucionPaciente.
 */
@RestController
@RequestMapping("/api")
public class EvolucionPacienteResource {

    private final Logger log = LoggerFactory.getLogger(EvolucionPacienteResource.class);

    private static final String ENTITY_NAME = "evolucionPaciente";

    private final EvolucionPacienteRepository evolucionPacienteRepository;

    private final ExamenComplementarioRepository examenComplementarioRepository;

    private final PlantillaInformeRepository plantillaInformeRepository;

    private final PlantillaRecipeRepository plantillaRecipeRepository;

    private final HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository;

    private final HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository;

    private final HistoriaPersonalNinoRepository historiaPersonalNinoRepository;

    private final HistoriaMedicaNinoRepository historiaMedicaNinoRepository;

    private final PerfilRepository perfilRepository;

    private final CabeceraInformeRepository cabeceraInformeRepository;

    private final PieDePaginaInformeRepository pieDePaginaInformeRepository;

    private final PlantillaAntecedentesRepository plantillaAntecedentesRepository;

    private MailService mailService;

    private JavaMailSenderImpl javaMailSender;

    private JHipsterProperties jHipsterProperties;

    public EvolucionPacienteResource(EvolucionPacienteRepository evolucionPacienteRepository, ExamenComplementarioRepository examenComplementarioRepository,
        PlantillaInformeRepository plantillaInformeRepository, PlantillaRecipeRepository plantillaRecipeRepository,
        HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository, HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository,
        HistoriaPersonalNinoRepository historiaPersonalNinoRepository, HistoriaMedicaNinoRepository historiaMedicaNinoRepository,
        MailService mailService, JavaMailSenderImpl javaMailSender, JHipsterProperties jHipsterProperties, PerfilRepository perfilRepository,
        CabeceraInformeRepository cabeceraInformeRepository, PieDePaginaInformeRepository pieDePaginaInformeRepository, PlantillaAntecedentesRepository plantillaAntecedentesRepository) {
        this.evolucionPacienteRepository = evolucionPacienteRepository;
        this.examenComplementarioRepository = examenComplementarioRepository;
        this.plantillaInformeRepository = plantillaInformeRepository;
        this.plantillaRecipeRepository = plantillaRecipeRepository;
        this.historiaMedicaAdultoRepository = historiaMedicaAdultoRepository;
        this.historiaMedicaNinoRepository = historiaMedicaNinoRepository;
        this.historiaPersonalAdultoRepository = historiaPersonalAdultoRepository;
        this.historiaPersonalNinoRepository = historiaPersonalNinoRepository;
        this.mailService = mailService;
        this.javaMailSender = javaMailSender;
        this.jHipsterProperties = jHipsterProperties;
        this.perfilRepository = perfilRepository;
        this.cabeceraInformeRepository = cabeceraInformeRepository;
        this.pieDePaginaInformeRepository = pieDePaginaInformeRepository;
        this.plantillaAntecedentesRepository = plantillaAntecedentesRepository;
    }

    /**
     * POST  /evolucion-pacientes : Create a new evolucionPaciente.
     *
     * @param evolucionPaciente the evolucionPaciente to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evolucionPaciente, or with status 400 (Bad Request) if the evolucionPaciente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evolucion-pacientes")
    public ResponseEntity<EvolucionPaciente> createEvolucionPaciente(@Valid @RequestBody EvolucionPaciente evolucionPaciente) throws URISyntaxException {
        log.debug("REST request to save EvolucionPaciente : {}", evolucionPaciente);
        if (evolucionPaciente.getId() != null) {
            throw new BadRequestAlertException("A new evolucionPaciente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvolucionPaciente result = evolucionPacienteRepository.save(evolucionPaciente);
        return ResponseEntity.created(new URI("/api/evolucion-pacientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evolucion-pacientes : Updates an existing evolucionPaciente.
     *
     * @param evolucionPaciente the evolucionPaciente to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evolucionPaciente,
     * or with status 400 (Bad Request) if the evolucionPaciente is not valid,
     * or with status 500 (Internal Server Error) if the evolucionPaciente couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evolucion-pacientes")
    public ResponseEntity<EvolucionPaciente> updateEvolucionPaciente(@Valid @RequestBody EvolucionPaciente evolucionPaciente) throws URISyntaxException {
        log.debug("REST request to update EvolucionPaciente : {}", evolucionPaciente);
        if (evolucionPaciente.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EvolucionPaciente result = evolucionPacienteRepository.save(evolucionPaciente);

        try {
            for (ExamenComplementario ex : evolucionPaciente.getExamenesComplementarios()) {
                if(ex.getId() != null){
                    if (examenComplementarioRepository.findById(ex.getId()).isPresent()) {
                        ex.setArchivo(examenComplementarioRepository.findById(ex.getId()).get().getArchivo());
                    }
                }
            }

            List<ExamenComplementario> examenes = examenComplementarioRepository.findByEvolucionPaciente_Id(result.getId());
            for (ExamenComplementario ex : examenes) {
                examenComplementarioRepository.delete(ex);
            }

            for (ExamenComplementario ex : evolucionPaciente.getExamenesComplementarios()) {
                ex.setEvolucionPaciente(result);
                examenComplementarioRepository.save(ex);
            }
        } catch (Exception exc) {

        }

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evolucionPaciente.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evolucion-pacientes : get all the evolucionPacientes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of evolucionPacientes in body
     */
    @GetMapping("/evolucion-pacientes")
    public ResponseEntity<List<EvolucionPaciente>> getAllEvolucionPacientes(Pageable pageable) {
        log.debug("REST request to get a page of EvolucionPacientes");
        Page<EvolucionPaciente> page = evolucionPacienteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evolucion-pacientes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /evolucion-pacientes/:id : get the "id" evolucionPaciente.
     *
     * @param id the id of the evolucionPaciente to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evolucionPaciente, or with status 404 (Not Found)
     */
    @GetMapping("/evolucion-pacientes/{id}")
    public ResponseEntity<EvolucionPaciente> getEvolucionPaciente(@PathVariable Long id) {
        log.debug("REST request to get EvolucionPaciente : {}", id);
        Optional<EvolucionPaciente> evolucionPaciente = evolucionPacienteRepository.findById(id);

        evolucionPaciente.get().setExamenesComplementarios(examenComplementarioRepository.findByEvolucionPaciente_Id(evolucionPaciente.get().getId()));

        for (ExamenComplementario ex : evolucionPaciente.get().getExamenesComplementarios()) {
            if(ex.getId() != null){
                ex.setArchivo(null);
            }
        }

        return ResponseUtil.wrapOrNotFound(evolucionPaciente);
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/evolucion-pacientes/informe/{id}/{individual}")
    public ResponseEntity<EvolucionPaciente> getInformeFile(@PathVariable Long id, @PathVariable Boolean individual) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<EvolucionPaciente> evolucionPaciente = evolucionPacienteRepository.findById(id);

        String file;
        if(individual) {
            file = getPdf(evolucionPaciente.get(), plantillaInformeRepository.findByEspecialidad_Id(evolucionPaciente.get().getCita().getEspecialidad().getId()).get(0).getTexto());
        } else {
            file = getPdfGrupal(evolucionPaciente.get(), plantillaInformeRepository.findByEspecialidad_Id(evolucionPaciente.get().getCita().getEspecialidad().getId()).get(0).getTexto());
        }
        evolucionPaciente.get().setInformeArchivo(file);

        return ResponseUtil.wrapOrNotFound(evolucionPaciente);
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/evolucion-pacientes/informe/enviar/{id}/{recipientes}/{individual}")
    public ResponseEntity<EvolucionPaciente> sendInformeFile(@PathVariable Long id, @PathVariable String recipientes, @PathVariable Boolean individual) throws IOException, DocumentException, IllegalAccessException, MessagingException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<EvolucionPaciente> evolucionPaciente = evolucionPacienteRepository.findById(id);

        String file;
        if(individual) {
            file = getPdf(evolucionPaciente.get(), plantillaInformeRepository.findByEspecialidad_Id(evolucionPaciente.get().getCita().getEspecialidad().getId()).get(0).getTexto());
        } else {
            file = getPdfGrupal(evolucionPaciente.get(), plantillaInformeRepository.findByEspecialidad_Id(evolucionPaciente.get().getCita().getEspecialidad().getId()).get(0).getTexto());
        }

        evolucionPaciente.get().setInformeArchivo(file);

        String[] correos = recipientes.split(",");

        Cita result = evolucionPaciente.get().getCita();

        for(String correo : correos) {
            //Enviar al doctor
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);

            String fechaConsulta = "";
            String soloFecha = "";
            if(result.getFecha() != null) {
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm a");
                fechaConsulta += "Fecha: ";
                fechaConsulta += result.getFecha().minusHours(4L).format(formatterDate);
                fechaConsulta += " - Hora: ";
                fechaConsulta += result.getFecha().minusHours(4L).format(formatterHour);
                soloFecha = result.getFecha().minusHours(4L).format(formatterDate);
            }

            String medicoTratante = "";
            if(result.getEspecialidadMedico() != null) {
                medicoTratante = "<li>Médico Tratante: " + result.getEspecialidadMedico().getMedico().getFirstName() + " " + result.getEspecialidadMedico().getMedico().getLastName() + "</li>";
            }

            message.setTo(correo);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject("Informe cita " + soloFecha);
            message.setText("Buenas," +
                "<br/><br/>Le enviamos el informe y récipes de Cita con los siguientes datos:<br/>" +
                "<ul>" +
                "<li>Usuario:  " + result.getPaciente().getEmail() + " </li>" +
                "<li>Especialidad: " + result.getEspecialidad().getEspecialidad() + "</li>" +
                medicoTratante +
                "<li>Tipo de consulta: " + result.getTipoConsulta().getNombre() + "</li>" +
                "<li>" + fechaConsulta + "</li>" +
                "</ul>" +
                "<br/>Atentamente,<br/><br/><i>Equipo de Innap Citas</i>", true);

            DataSource datasource;
            datasource = new ByteArrayDataSource(Base64.decode(file), "application/pdf");

            message.addAttachment("Informe" + id + ".pdf", datasource);

            String[] recipes = evolucionPaciente.get().getRecipe().split("\",\"");
            String[] indicaciones = evolucionPaciente.get().getIndicaciones().split("\",\"");

            for (Long i = 0L; i < recipes.length; i++) {
                DataSource datasource2;
                datasource2 = new ByteArrayDataSource(Base64.decode(getRecipeFileString(evolucionPaciente, i)), "application/pdf");
                message.addAttachment("Recipe" + id + "_" + (i + 1) + ".pdf", datasource2);
            }

            for (Long j = 0L; j < indicaciones.length; j++) {
                DataSource datasource2;
                datasource2 = new ByteArrayDataSource(Base64.decode(getIndicacionFileString(evolucionPaciente, j)), "application/pdf");
                message.addAttachment("Indicacion" + id + "_" + (j + 1) + ".pdf", datasource2);
            }

            javaMailSender.send(mimeMessage);
        }

        return ResponseUtil.wrapOrNotFound(evolucionPaciente);
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/evolucion-pacientes/recipe/{id}/{item}")
    public ResponseEntity<EvolucionPaciente> getRecipeFile(@PathVariable Long id, @PathVariable Long item) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<EvolucionPaciente> evolucionPaciente = evolucionPacienteRepository.findById(id);

        evolucionPaciente.get().setRecipeArchivo(getRecipeFileString(evolucionPaciente, item));

        return ResponseUtil.wrapOrNotFound(evolucionPaciente);
    }

    private String getRecipeFileString(Optional<EvolucionPaciente> evolucionPaciente, Long item) throws DocumentException, IllegalAccessException, IOException {
        String recipe = "";

        String[] recipes = evolucionPaciente.get().getRecipe().split("\",\"");
        recipe = recipes[Integer.parseInt(item.toString())];
        recipe = recipe.replace("[\"","");
        recipe = recipe.replace("\"]","");
        recipe = recipe.replace("\\n","");

        String template = plantillaRecipeRepository.findByMedico_Id(evolucionPaciente.get().getCita().getEspecialidadMedico().getMedico().getId()).get(0).getTexto();

        Cita cita = evolucionPaciente.get().getCita();

        String nombre = null;
        String fechaNacimiento = null;
        String edad = null;
        String cedula = null;
        String sexo = null;
        String email = null;
        String telefono = null;
        if(cita.isPacienteNino()) {
            for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null) {
                    sexo = historiaPersonal.getSexo().getNombre();
                }
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                    telefono = historiaPersonal.getTelefonoCelularRepresentante();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        } else {
            for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null) {
                    sexo = historiaPersonal.getSexo().getNombre();
                }
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        }

        if(nombre != null)
            template = template.replace("@pacienteNombre", nombre);
        else
            template = template.replace("@pacienteNombre", "");
        if(fechaNacimiento != null)
            template = template.replace("@pacienteFechaNacimiento", fechaNacimiento);
        else
            template = template.replace("@pacienteFechaNacimiento", "");
        if(edad != null)
            template = template.replace("@pacienteEdad", edad);
        else
            template = template.replace("@pacienteEdad", "");
        if(cedula != null)
            template = template.replace("@pacienteCedula", cedula);
        else
            template = template.replace("@pacienteCedula", "");
        if(sexo != null)
            template = template.replace("@pacienteSexo", sexo);
        else
            template = template.replace("@pacienteSexo", "");
        if(telefono != null)
            template = template.replace("@pacienteTelefono", telefono);
        else
            template = template.replace("@pacienteTelefono", "");
        if(email != null)
            template = template.replace("@pacienteEmail", email);
        else
            template = template.replace("@pacienteEmail", "");

        template = template.replace("@fecha", getFecha(LocalDate.now()));

        template = template.replace("@selloMedico", getFirma(evolucionPaciente.get().getCita().getEspecialidadMedico().getMedico()));

        String file = getPdf("Récipe: <br/><br/>" + recipe, template);

        return file;
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/evolucion-pacientes/indicaciones/{id}/{item}")
    public ResponseEntity<EvolucionPaciente> getIndicacionesFile(@PathVariable Long id, @PathVariable Long item) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<EvolucionPaciente> evolucionPaciente = evolucionPacienteRepository.findById(id);

        evolucionPaciente.get().setIndicacionesArchivo(getIndicacionFileString(evolucionPaciente, item));

        return ResponseUtil.wrapOrNotFound(evolucionPaciente);
    }

    private String getIndicacionFileString(Optional<EvolucionPaciente> evolucionPaciente, Long item) throws DocumentException, IllegalAccessException, IOException {
        String indicacion = "";

        String[] indicaciones = evolucionPaciente.get().getIndicaciones().split("\",\"");
        indicacion = indicaciones[Integer.parseInt(item.toString())];
        indicacion = indicacion.replace("[\"","");
        indicacion = indicacion.replace("\"]","");
        indicacion = indicacion.replace("\\n","");

        String template = plantillaRecipeRepository.findByMedico_Id(evolucionPaciente.get().getCita().getEspecialidadMedico().getMedico().getId()).get(0).getTexto();

        Cita cita = evolucionPaciente.get().getCita();

        String nombre = null;
        String fechaNacimiento = null;
        String edad = null;
        String cedula = null;
        String sexo = null;
        String email = null;
        String telefono = null;
        if(cita.isPacienteNino()) {
            for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null)
                    sexo = historiaPersonal.getSexo().getNombre();
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                    telefono = historiaPersonal.getTelefonoCelularRepresentante();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        } else {
            for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null)
                    sexo = historiaPersonal.getSexo().getNombre();
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        }

        if(nombre != null)
            template = template.replace("@pacienteNombre", nombre);
        else
            template = template.replace("@pacienteNombre", "");
        if(fechaNacimiento != null)
            template = template.replace("@pacienteFechaNacimiento", fechaNacimiento);
        else
            template = template.replace("@pacienteFechaNacimiento", "");
        if(edad != null)
            template = template.replace("@pacienteEdad", edad);
        else
            template = template.replace("@pacienteEdad", "");
        if(cedula != null)
            template = template.replace("@pacienteCedula", cedula);
        else
            template = template.replace("@pacienteCedula", "");
        if(sexo != null)
            template = template.replace("@pacienteSexo", sexo);
        else
            template = template.replace("@pacienteSexo", "");
        if(telefono != null)
            template = template.replace("@pacienteTelefono", telefono);
        else
            template = template.replace("@pacienteTelefono", "");
        if(email != null)
            template = template.replace("@pacienteEmail", email);
        else
            template = template.replace("@pacienteEmail", "");

        template = template.replace("@fecha", getFecha(LocalDate.now()));

        template = template.replace("@selloMedico", getFirma(evolucionPaciente.get().getCita().getEspecialidadMedico().getMedico()));

        String file = getPdf("Indicaciones: <br/><br/>" + indicacion, template);

        return file;
    }

    public String getPdf(String texto, String template) throws IOException, DocumentException, IllegalAccessException {

        template = template.replace("@texto", texto);

        String resultado = generarPdfFromHtmlChiquitico(template);

        return resultado;
    }

    private String ingresarInfo(String resultadoImpresion, String variable, String valor) {
        if(resultadoImpresion.contains("%1" + variable) && resultadoImpresion.contains("%2" + variable)) {
            resultadoImpresion = resultadoImpresion.replace("%1" + variable, "");
            resultadoImpresion = resultadoImpresion.replace("%2" + variable, "");
        }
        resultadoImpresion = resultadoImpresion.replace("@" + variable, valor);
        return resultadoImpresion;
    }

    private String eliminarInfo(String resultadoImpresion, String variable) {
        String sectionDeleter = "";
        if(resultadoImpresion.contains("%1" + variable) && resultadoImpresion.contains("%2" + variable)) {
            sectionDeleter = resultadoImpresion.split("%1" + variable)[1];
            sectionDeleter = sectionDeleter.split("%2" + variable)[0];
            resultadoImpresion = resultadoImpresion.replace(sectionDeleter, "");
            resultadoImpresion = resultadoImpresion.replace("%1" + variable, "");
            resultadoImpresion = resultadoImpresion.replace("%2" + variable, "");
        } else {
            resultadoImpresion = ingresarInfo(resultadoImpresion,variable, "");
        }
        return resultadoImpresion;
    }

    public String getPdfGrupal(EvolucionPaciente historiaInicial, String templateInicial) throws IOException, DocumentException, IllegalAccessException {

        String resultadoImpresion = "";
        String sectionDeleter = "";

        List<EvolucionPaciente> todosInformes = evolucionPacienteRepository.findByCita_Paciente_Id(historiaInicial.getCita().getPaciente().getId());

        List<CabeceraInforme> cabeceras = cabeceraInformeRepository.findAll();
        String header = "";
        if(cabeceras.size() > 0) {
            header = cabeceras.get(0).getCabecera();
            header = header.replace("@fechaActual", getFecha(LocalDate.now()));
            if(historiaInicial.getCita().getPaciente().getNumeroHistoria() != null)
                header = header.replace("@pacienteNumeroHistoria", historiaInicial.getCita().getPaciente().getNumeroHistoria().toString());
            else
                header = header.replace("@pacienteNumeroHistoria", "");
            header = header.replace("@id", historiaInicial.getId().toString());

            Cita cita = historiaInicial.getCita();

            String nombre = null;
            String fechaNacimiento = null;
            String edad = null;
            String cedula = null;
            String sexo = null;
            String email = null;
            String telefono = null;
            if(cita.isPacienteNino()) {
                for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                        telefono = historiaPersonal.getTelefonoCelularRepresentante();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            } else {
                for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            }

            if(nombre != null)
                header = header.replace("@pacienteNombre", nombre);
            else
                header = header.replace("@pacienteNombre", "");
            if(fechaNacimiento != null)
                header = header.replace("@pacienteFechaNacimiento", fechaNacimiento);
            else
                header = header.replace("@pacienteFechaNacimiento", "");
            if(edad != null)
                header = header.replace("@pacienteEdad", edad);
            else
                header = header.replace("@pacienteEdad", "");
            if(cedula != null)
                header = header.replace("@pacienteCedula", cedula);
            else
                header = header.replace("@pacienteCedula", "");
            if(sexo != null)
                header = header.replace("@pacienteSexo", sexo);
            else
                header = header.replace("@pacienteSexo", "");
            if(telefono != null)
                header = header.replace("@pacienteTelefono", telefono);
            else
                header = header.replace("@pacienteTelefono", "");
            if(email != null)
                header = header.replace("@pacienteEmail", email);
            else
                header = header.replace("@pacienteEmail", "");
        }

        List<PlantillaAntecedentes> antecedentes = plantillaAntecedentesRepository.findAll();
        String antecedentesStr = "";
        if (antecedentes.size() > 0) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// pasarintro1
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            antecedentesStr = antecedentes.get(0).getAntecedentes();

            Boolean llenadoAntecedentes = false;

            antecedentesStr = antecedentesStr.replace("@fechaActual", getFecha(LocalDate.now()));
            if(historiaInicial.getCita().getPaciente().getNumeroHistoria() != null)
                antecedentesStr = antecedentesStr.replace("@pacienteNumeroHistoria", historiaInicial.getCita().getPaciente().getNumeroHistoria().toString());
            else
                antecedentesStr = antecedentesStr.replace("@pacienteNumeroHistoria", "");
            antecedentesStr = antecedentesStr.replace("@id", historiaInicial.getId().toString());

            Cita cita = historiaInicial.getCita();
            User medico = cita.getEspecialidadMedico().getMedico();

            String nombre = null;
            String fechaNacimiento = null;
            String edad = null;
            String cedula = null;
            String sexo = null;
            String email = null;
            String telefono = null;
            if(cita.isPacienteNino()) {
                for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                        telefono = historiaPersonal.getTelefonoCelularRepresentante();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            } else {
                for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            }

            if(nombre != null)
                antecedentesStr = antecedentesStr.replace("@pacienteNombre", nombre);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteNombre", "");
            if(fechaNacimiento != null)
                antecedentesStr = antecedentesStr.replace("@pacienteFechaNacimiento", fechaNacimiento);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteFechaNacimiento", "");
            if(edad != null)
                antecedentesStr = antecedentesStr.replace("@pacienteEdad", edad);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteEdad", "");
            if(cedula != null)
                antecedentesStr = antecedentesStr.replace("@pacienteCedula", cedula);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteCedula", "");
            if(sexo != null)
                antecedentesStr = antecedentesStr.replace("@pacienteSexo", sexo);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteSexo", "");
            if(telefono != null)
                antecedentesStr = antecedentesStr.replace("@pacienteTelefono", telefono);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteTelefono", "");
            if(email != null)
                antecedentesStr = antecedentesStr.replace("@pacienteEmail", email);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteEmail", "");

            if(cita.isPacienteNino()) {
                for(HistoriaMedicaNino historiaMedica: historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    Boolean llenadosMedicamentos = false;
                    if (historiaMedica.getMedicamento1() != null) {
                        Gson gson = new Gson();

                        Medicamento[] medicamentoArray = gson.fromJson(historiaMedica.getMedicamento1(), Medicamento[].class);

                        String medicamentos = "";
                        for(Medicamento medicamento : medicamentoArray) {
                            String tipo = " - ";
                            if(medicamento.getTipo() == 1L) {
                                tipo = "recetado";
                            } else if(medicamento.getTipo() == 2L) {
                                tipo = "automedicado";
                            } else if(medicamento.getTipo() == 3L) {
                                tipo = "alternativo";
                            }

                            if((medicamento.getNombre() != null) && (!medicamento.getNombre().equals("null"))) {
                                llenadosMedicamentos = true;
                                medicamentos += "<b>" + medicamento.getNombre() + ":</b><br/>Cantidad: " + medicamento.getCantidad() + "<br/>Dosis: " + medicamento.getDosis() + "<br/>Frecuencia: " + medicamento.getFrecuencia() + "<br/>Tipo: " + tipo + "<br/><br/>";
                            }
                        }

                        if(llenadosMedicamentos) {
                            antecedentesStr = ingresarInfo(antecedentesStr,"habitos_medicamentosos", medicamentos);
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                        }

                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                    }

                    Boolean llenadoHistoria = false;
                    Object someObject = historiaMedica;
                    for (Field field : someObject.getClass().getDeclaredFields()) {
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = field.get(someObject);
                        if (value != null) {
                            if(value.toString().equals("true")) {
                                value = "Sí";
                            }
                            if(value.toString().equals("false")) {
                                value = "No";
                            }
                            if(!field.getName().equals("paciente") && antecedentesStr.contains("@" + field.getName())) {
                                llenadoHistoria = true;
                                antecedentesStr = ingresarInfo(antecedentesStr,field.getName(), value.toString());
                            }
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, field.getName());
                        }
                    }
                    if (llenadoHistoria) {
                        antecedentesStr = ingresarInfo(antecedentesStr,"antecendentesMedicos", "");
                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "antecendentesMedicos");
                    }
                    if(llenadoHistoria || llenadosMedicamentos) {
                        llenadoAntecedentes = true;
                    }
                    break;
                }
            } else {
                for(HistoriaMedicaAdulto historiaMedica: historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    Boolean llenadosMedicamentos = false;
                    if (historiaMedica.getMedicamento1() != null) {
                        Gson gson = new Gson();

                        Medicamento[] medicamentoArray = gson.fromJson(historiaMedica.getMedicamento1(), Medicamento[].class);

                        String medicamentos = "";
                        for(Medicamento medicamento : medicamentoArray) {
                            String tipo = " - ";
                            if(medicamento.getTipo() == 1L) {
                                tipo = "recetado";
                            } else if(medicamento.getTipo() == 2L) {
                                tipo = "automedicado";
                            } else if(medicamento.getTipo() == 3L) {
                                tipo = "alternativo";
                            }

                            if((medicamento.getNombre() != null) && (!medicamento.getNombre().equals("null"))) {
                                llenadosMedicamentos = true;
                                medicamentos += "<b>" + medicamento.getNombre() + ":</b><br/>Cantidad: " + medicamento.getCantidad() + "<br/>Dosis: " + medicamento.getDosis() + "<br/>Frecuencia: " + medicamento.getFrecuencia() + "<br/>Tipo: " + tipo + "<br/><br/>";
                            }
                        }

                        if(llenadosMedicamentos) {
                            antecedentesStr = ingresarInfo(antecedentesStr,"habitos_medicamentosos", medicamentos);
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                        }

                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                    }

                    Boolean llenadoHistoria = false;
                    Object someObject = historiaMedica;
                    for (Field field : someObject.getClass().getDeclaredFields()) {
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = field.get(someObject);
                        if (value != null) {
                            if(value.toString().equals("true")) {
                                value = "Sí";
                            }
                            if(value.toString().equals("false")) {
                                value = "No";
                            }
                            if(!field.getName().equals("paciente") && antecedentesStr.contains("@" + field.getName())) {
                                llenadoHistoria = true;
                                antecedentesStr = ingresarInfo(antecedentesStr,field.getName(), value.toString());
                            }
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, field.getName());
                        }
                    }
                    if (llenadoHistoria) {
                        antecedentesStr = ingresarInfo(antecedentesStr,"antecendentesMedicos", "");
                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "antecendentesMedicos");
                    }
                    if(llenadoHistoria || llenadosMedicamentos) {
                        llenadoAntecedentes = true;
                    }
                    break;
                }
            }

            if (llenadoAntecedentes) {
                antecedentesStr = ingresarInfo(antecedentesStr,"antecedentesCompletos", "");
            } else {
                antecedentesStr = eliminarInfo(antecedentesStr, "antecedentesCompletos");
            }

            if(medico != null) {
                if(medico.getTelefono() != null) {
                    antecedentesStr = antecedentesStr.replace("@medicoTelefono", medico.getTelefono());
                }else{
                    antecedentesStr = antecedentesStr.replace("@medicoTelefono", "");
                }

                antecedentesStr = antecedentesStr.replace("@medicoCorreo", medico.getEmail());

                if(medico.getTelefono() != null) {
                    antecedentesStr = antecedentesStr.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
                }else{
                    antecedentesStr = antecedentesStr.replace("@medicoNombre", "");
                }
            } else {
                antecedentesStr = antecedentesStr.replace("@medicoTelefono", "");
                antecedentesStr = antecedentesStr.replace("@medicoCorreo", "");
                antecedentesStr = antecedentesStr.replace("@medicoNombre", "");
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// pasarintro2
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        resultadoImpresion += antecedentesStr;

        for(EvolucionPaciente historia : todosInformes) {
            String template = templateInicial;

            Object someObject2 = historia;

            Cita cita = historia.getCita();

            User medico = cita.getEspecialidadMedico().getMedico();

            String nombre = null;
            String fechaNacimiento = null;
            String edad = null;
            String cedula = null;
            String sexo = null;
            String email = null;
            String telefono = null;
            if(cita.isPacienteNino()) {
                for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                        telefono = historiaPersonal.getTelefonoCelularRepresentante();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            } else {
                for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            }

            if(medico != null) {
                if(medico.getTelefono() != null) {
                    template = template.replace("@medicoTelefono", medico.getTelefono());
                }else{
                    template = template.replace("@medicoTelefono", "");
                }

                template = template.replace("@medicoCorreo", medico.getEmail());

                if(medico.getTelefono() != null) {
                    template = template.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
                }else{
                    template = template.replace("@medicoNombre", "");
                }
            } else {
                template = template.replace("@medicoTelefono", "");
                template = template.replace("@medicoCorreo", "");
                template = template.replace("@medicoNombre", "");
            }

            if(cita.getFecha() != null)
                template = template.replace("@citaFecha", getFecha(cita.getFecha()));
            else
                template = template.replace("@citaFecha", "");

            if(nombre != null)
                template = template.replace("@pacienteNombre", nombre);
            else
                template = template.replace("@pacienteNombre", "");
            if(fechaNacimiento != null)
                template = template.replace("@pacienteFechaNacimiento", fechaNacimiento);
            else
                template = template.replace("@pacienteFechaNacimiento", "");
            if(edad != null)
                template = template.replace("@pacienteEdad", edad);
            else
                template = template.replace("@pacienteEdad", "");
            if(cedula != null)
                template = template.replace("@pacienteCedula", cedula);
            else
                template = template.replace("@pacienteCedula", "");
            if(sexo != null)
                template = template.replace("@pacienteSexo", sexo);
            else
                template = template.replace("@pacienteSexo", "");
            if(telefono != null)
                template = template.replace("@pacienteTelefono", telefono);
            else
                template = template.replace("@pacienteTelefono", "");
            if(email != null)
                template = template.replace("@pacienteEmail", email);
            else
                template = template.replace("@pacienteEmail", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            template = template.replace("@fechaActual", getFecha(LocalDate.now()));

            if(historia.getFecha() != null){
                template = template.replace("@fecha", getFechaSinHora(historia.getFecha()));
                template = template.replace("@hora", getHora(historia.getFecha()));
            } else {
                template = template.replace("@fecha", "");
                template = template.replace("@hora", "");
            }

            if(cita.getPaciente().getNumeroHistoria() != null)
                template = template.replace("@pacienteNumeroHistoria", cita.getPaciente().getNumeroHistoria().toString());
            else
                template = template.replace("@pacienteNumeroHistoria", "");

            if(historia.getNuevosSintomas() != null)
                template = ingresarInfo(template,"nuevosSintomas", historia.getNuevosSintomas());
            else
                template = eliminarInfo(template,"nuevosSintomas");

            if(historia.getRecomendacionesGenerales() != null)
                template = ingresarInfo(template, "recomendacionesGenerales", historia.getRecomendacionesGenerales());
            else
                template = eliminarInfo(template, "recomendacionesGenerales");

            if(historia.getRecomendaciones() != null)
                template = ingresarInfo(template, "recomendaciones", historia.getRecomendaciones());
            else
                template = eliminarInfo(template, "recomendaciones");

            historia.setExamenesComplementarios(examenComplementarioRepository.findByEvolucionPaciente_Id(historia.getId()));
            String examenesComplementarios = "";
            if(historia.getExamenesComplementarios() != null) {
                for(ExamenComplementario ex : historia.getExamenesComplementarios()) {
                    if (ex.getArchivoContentType() != null) {
                        String descripcion = ex.getDescripcion();
                        if(descripcion == null){
                            descripcion = "";
                        }
                        if (ex.getArchivoContentType().contains("image")) {
                            examenesComplementarios += descripcion + "<br/><br/>" + getImagen(ex) + "<br/>";
                        } else {
                            examenesComplementarios += "<a href=\"http://innap.net/citas/?examen-complementario/" + ex.getId() + "/view\">" + descripcion + "</a>" + "<br/>";
                        }
                    }
                }
            }

            if(!examenesComplementarios.equalsIgnoreCase(""))
                template = ingresarInfo(template, "examenesComplementarios", examenesComplementarios);
            else
                template = eliminarInfo(template, "examenesComplementarios");

            if (historia.getTratamiento() != null) {
                Gson gson = new Gson();

                Tratamiento[] tratamientoArray = gson.fromJson(historia.getTratamiento(), Tratamiento[].class);

                String tratamientos = "";
                Boolean llenadoTratamiento = false;
                for(Tratamiento tratamiento : tratamientoArray) {
                    if (tratamiento.getNombre() != null) {
                        llenadoTratamiento = true;
                        if (tratamiento.getIndicacion() == null) {
                            tratamientos += tratamiento.getNombre() + "<br/>";
                        } else {
                            tratamientos += tratamiento.getNombre() + ": " + tratamiento.getIndicacion() + "<br/>";
                        }
                    }

                }
                if(llenadoTratamiento) {
                    template = ingresarInfo(template, "tratamiento", tratamientos);
                } else {
                    template = eliminarInfo(template, "tratamiento");
                }

            } else {
                template = eliminarInfo(template, "tratamiento");
            }

            if (historia.getExamenesIndicados() != null) {
                Gson gson = new Gson();

                ExamenIndicado[] examenIndicadoArray = gson.fromJson(historia.getExamenesIndicados(), ExamenIndicado[].class);

                String examenes = "";
                Boolean llenadoExamenes = false;
                for(ExamenIndicado examenIndicado : examenIndicadoArray) {
                    if (examenIndicado.getExamen() != null) {
                        llenadoExamenes = true;
                        if (examenIndicado.getReferencia() == null) {
                            examenes += examenIndicado.getExamen() + "<br/>";
                        } else {
                            examenes += examenIndicado.getExamen() + ": " + examenIndicado.getReferencia() + "<br/>";
                        }
                    }
                }
                if(llenadoExamenes) {
                    template = ingresarInfo(template, "examenesIndicados", examenes);
                } else {
                    template = eliminarInfo(template, "examenesIndicados");
                }

            } else {
                template = eliminarInfo(template, "examenesIndicados");
            }

            String consultaOnlineText = "";
            if (historia.getCita().getConsultaOnline() != null && historia.getCita().getConsultaOnline()) {
                consultaOnlineText = "Online";
            } else {
                consultaOnlineText = "Presencial";
            }
            template = template.replace("@consultaOnline",consultaOnlineText);

            if(historia.getCita().getTipoConsulta() != null)
                template = template.replace("@tipoConsulta", historia.getCita().getTipoConsulta().getNombre());
            else
                template = template.replace("@tipoConsulta", "");


            for (Field field : someObject2.getClass().getDeclaredFields()) {
                field.setAccessible(true); // You might want to set modifier to public first.
                Object value = field.get(someObject2);
                if (value != null) {
                    template = ingresarInfo(template, field.getName(), value.toString());
                } else {
                    template = eliminarInfo(template, field.getName());
                }
            }

            template = template.replace("@selloMedico", getFirma(historia.getCita().getEspecialidadMedico().getMedico()));

            if (historiaInicial.getFecha() == null && historiaInicial.getPeso() == null && historiaInicial.gettAAcostada() == null &&
                historiaInicial.gettASentada() == null && historiaInicial.gettAParada() == null && historiaInicial.getTalla() == null &&
                historiaInicial.getImc() == null && historiaInicial.getFirma() == null) {
                template = eliminarInfo(template, "signosVitales");
            } else {
                template = ingresarInfo(template, "signosVitales", "");
            }

            if (historiaInicial.getLateralidadMano() == null && historiaInicial.getLateralidadOido() == null && historiaInicial.getLateralidadOjo() == null &&
                historiaInicial.getLateralidadPierna() == null) {
                template = eliminarInfo(template, "lateralidad");
            } else {
                template = ingresarInfo(template, "lateralidad", "");
            }

            resultadoImpresion += template;
        }

        List<PieDePaginaInforme> pies = pieDePaginaInformeRepository.findAll();
        String footer = "";
        if(pies.size() > 0) {

            PieDePaginaInforme pie = pies.get(0);

            footer += pie.getPieDePagina();

            Cita cita = historiaInicial.getCita();
            User medico = cita.getEspecialidadMedico().getMedico();

            if(medico != null) {
                if(medico.getTelefono() != null) {
                    footer = footer.replace("@medicoTelefono", medico.getTelefono());
                }else{
                    footer = footer.replace("@medicoTelefono", "");
                }

                footer = footer.replace("@medicoCorreo", medico.getEmail());

                if(medico.getTelefono() != null) {
                    footer = footer.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
                }else{
                    footer = footer.replace("@medicoNombre", "");
                }
            } else {
                footer = footer.replace("@medicoTelefono", "");
                footer = footer.replace("@medicoCorreo", "");
                footer = footer.replace("@medicoNombre", "");
            }

            footer = footer.replace("@selloMedico", getFirma(historiaInicial.getCita().getEspecialidadMedico().getMedico()));
        }

        String resultado = generarPdfFromHtml(resultadoImpresion, header, footer);

        return resultado;
    }

    public String getPdf(EvolucionPaciente historiaInicial, String templateInicial) throws IOException, DocumentException, IllegalAccessException {

        String resultadoImpresion = "";
        String sectionDeleter = "";

        List<EvolucionPaciente> todosInformes = evolucionPacienteRepository.findByCita_Paciente_Id(historiaInicial.getCita().getPaciente().getId());

        List<CabeceraInforme> cabeceras = cabeceraInformeRepository.findAll();
        String header = "";
        if(cabeceras.size() > 0) {
            header = cabeceras.get(0).getCabecera();
            header = header.replace("@fechaActual", getFecha(LocalDate.now()));
            if(historiaInicial.getCita().getPaciente().getNumeroHistoria() != null)
                header = header.replace("@pacienteNumeroHistoria", historiaInicial.getCita().getPaciente().getNumeroHistoria().toString());
            else
                header = header.replace("@pacienteNumeroHistoria", "");
            header = header.replace("@id", historiaInicial.getId().toString());

            Cita cita = historiaInicial.getCita();

            User medico = cita.getEspecialidadMedico().getMedico();

            String nombre = null;
            String fechaNacimiento = null;
            String edad = null;
            String cedula = null;
            String sexo = null;
            String email = null;
            String telefono = null;
            if(cita.isPacienteNino()) {
                for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                        telefono = historiaPersonal.getTelefonoCelularRepresentante();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            } else {
                for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            }

            if(nombre != null)
                header = header.replace("@pacienteNombre", nombre);
            else
                header = header.replace("@pacienteNombre", "");
            if(fechaNacimiento != null)
                header = header.replace("@pacienteFechaNacimiento", fechaNacimiento);
            else
                header = header.replace("@pacienteFechaNacimiento", "");
            if(edad != null)
                header = header.replace("@pacienteEdad", edad);
            else
                header = header.replace("@pacienteEdad", "");
            if(cedula != null)
                header = header.replace("@pacienteCedula", cedula);
            else
                header = header.replace("@pacienteCedula", "");
            if(sexo != null)
                header = header.replace("@pacienteSexo", sexo);
            else
                header = header.replace("@pacienteSexo", "");
            if(telefono != null)
                header = header.replace("@pacienteTelefono", telefono);
            else
                header = header.replace("@pacienteTelefono", "");
            if(email != null)
                header = header.replace("@pacienteEmail", email);
            else
                header = header.replace("@pacienteEmail", "");
        }

        List<PlantillaAntecedentes> antecedentes = plantillaAntecedentesRepository.findAll();
        String antecedentesStr = "";
        if (antecedentes.size() > 0) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// pasarintro1
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            antecedentesStr = antecedentes.get(0).getAntecedentes();

            Boolean llenadoAntecedentes = false;

            antecedentesStr = antecedentesStr.replace("@fechaActual", getFecha(LocalDate.now()));
            if(historiaInicial.getCita().getPaciente().getNumeroHistoria() != null)
                antecedentesStr = antecedentesStr.replace("@pacienteNumeroHistoria", historiaInicial.getCita().getPaciente().getNumeroHistoria().toString());
            else
                antecedentesStr = antecedentesStr.replace("@pacienteNumeroHistoria", "");
            antecedentesStr = antecedentesStr.replace("@id", historiaInicial.getId().toString());

            Cita cita = historiaInicial.getCita();
            User medico = cita.getEspecialidadMedico().getMedico();

            String nombre = null;
            String fechaNacimiento = null;
            String edad = null;
            String cedula = null;
            String sexo = null;
            String email = null;
            String telefono = null;
            if(cita.isPacienteNino()) {
                for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                        telefono = historiaPersonal.getTelefonoCelularRepresentante();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            } else {
                for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                    if(historiaPersonal.getFechaNacimiento() != null) {
                        fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                        edad = calculateAge(historiaPersonal.getFechaNacimiento());
                    }
                    cedula = historiaPersonal.getCedula();
                    if(historiaPersonal.getSexo() != null)
                        sexo = historiaPersonal.getSexo().getNombre();
                    if(historiaPersonal.getTelefonoCelular() != null) {
                        telefono = historiaPersonal.getTelefonoCelular();
                    }
                    if(historiaPersonal.getEmail() != null) {
                        email = historiaPersonal.getEmail();
                    }
                    break;
                }
            }

            if(nombre != null)
                antecedentesStr = antecedentesStr.replace("@pacienteNombre", nombre);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteNombre", "");
            if(fechaNacimiento != null)
                antecedentesStr = antecedentesStr.replace("@pacienteFechaNacimiento", fechaNacimiento);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteFechaNacimiento", "");
            if(edad != null)
                antecedentesStr = antecedentesStr.replace("@pacienteEdad", edad);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteEdad", "");
            if(cedula != null)
                antecedentesStr = antecedentesStr.replace("@pacienteCedula", cedula);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteCedula", "");
            if(sexo != null)
                antecedentesStr = antecedentesStr.replace("@pacienteSexo", sexo);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteSexo", "");
            if(telefono != null)
                antecedentesStr = antecedentesStr.replace("@pacienteTelefono", telefono);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteTelefono", "");
            if(email != null)
                antecedentesStr = antecedentesStr.replace("@pacienteEmail", email);
            else
                antecedentesStr = antecedentesStr.replace("@pacienteEmail", "");

            if(cita.isPacienteNino()) {
                for(HistoriaMedicaNino historiaMedica: historiaMedicaNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    Boolean llenadosMedicamentos = false;
                    if (historiaMedica.getMedicamento1() != null) {
                        Gson gson = new Gson();

                        Medicamento[] medicamentoArray = gson.fromJson(historiaMedica.getMedicamento1(), Medicamento[].class);

                        String medicamentos = "";
                        for(Medicamento medicamento : medicamentoArray) {
                            String tipo = " - ";
                            if(medicamento.getTipo() == 1L) {
                                tipo = "recetado";
                            } else if(medicamento.getTipo() == 2L) {
                                tipo = "automedicado";
                            } else if(medicamento.getTipo() == 3L) {
                                tipo = "alternativo";
                            }

                            if((medicamento.getNombre() != null) && (!medicamento.getNombre().equals("null"))) {
                                llenadosMedicamentos = true;
                                medicamentos += "<b>" + medicamento.getNombre() + ":</b><br/>Cantidad: " + medicamento.getCantidad() + "<br/>Dosis: " + medicamento.getDosis() + "<br/>Frecuencia: " + medicamento.getFrecuencia() + "<br/>Tipo: " + tipo + "<br/><br/>";
                            }
                        }

                        if(llenadosMedicamentos) {
                            antecedentesStr = ingresarInfo(antecedentesStr,"habitos_medicamentosos", medicamentos);
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                        }

                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                    }

                    Boolean llenadoHistoria = false;
                    Object someObject = historiaMedica;
                    for (Field field : someObject.getClass().getDeclaredFields()) {
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = field.get(someObject);
                        if (value != null) {
                            if(value.toString().equals("true")) {
                                value = "Sí";
                            }
                            if(value.toString().equals("false")) {
                                value = "No";
                            }
                            if(!field.getName().equals("paciente") && antecedentesStr.contains("@" + field.getName())) {
                                llenadoHistoria = true;
                                antecedentesStr = ingresarInfo(antecedentesStr,field.getName(), value.toString());
                            }
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, field.getName());
                        }
                    }
                    if (llenadoHistoria) {
                        antecedentesStr = ingresarInfo(antecedentesStr,"antecendentesMedicos", "");
                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "antecendentesMedicos");
                    }
                    if(llenadoHistoria || llenadosMedicamentos) {
                        llenadoAntecedentes = true;
                    }
                    break;
                }
            } else {
                for(HistoriaMedicaAdulto historiaMedica: historiaMedicaAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                    Boolean llenadosMedicamentos = false;
                    if (historiaMedica.getMedicamento1() != null) {
                        Gson gson = new Gson();

                        Medicamento[] medicamentoArray = gson.fromJson(historiaMedica.getMedicamento1(), Medicamento[].class);

                        String medicamentos = "";
                        for(Medicamento medicamento : medicamentoArray) {
                            String tipo = " - ";
                            if(medicamento.getTipo() == 1L) {
                                tipo = "recetado";
                            } else if(medicamento.getTipo() == 2L) {
                                tipo = "automedicado";
                            } else if(medicamento.getTipo() == 3L) {
                                tipo = "alternativo";
                            }

                            if((medicamento.getNombre() != null) && (!medicamento.getNombre().equals("null"))) {
                                llenadosMedicamentos = true;
                                medicamentos += "<b>" + medicamento.getNombre() + ":</b><br/>Cantidad: " + medicamento.getCantidad() + "<br/>Dosis: " + medicamento.getDosis() + "<br/>Frecuencia: " + medicamento.getFrecuencia() + "<br/>Tipo: " + tipo + "<br/><br/>";
                            }
                        }

                        if(llenadosMedicamentos) {
                            antecedentesStr = ingresarInfo(antecedentesStr,"habitos_medicamentosos", medicamentos);
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                        }

                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "habitos_medicamentosos");
                    }

                    Boolean llenadoHistoria = false;
                    Object someObject = historiaMedica;
                    for (Field field : someObject.getClass().getDeclaredFields()) {
                        field.setAccessible(true); // You might want to set modifier to public first.
                        Object value = field.get(someObject);
                        if (value != null) {
                            if(value.toString().equals("true")) {
                                value = "Sí";
                            }
                            if(value.toString().equals("false")) {
                                value = "No";
                            }
                            if(!field.getName().equals("paciente") && antecedentesStr.contains("@" + field.getName())) {
                                llenadoHistoria = true;
                                antecedentesStr = ingresarInfo(antecedentesStr,field.getName(), value.toString());
                            }
                        } else {
                            antecedentesStr = eliminarInfo(antecedentesStr, field.getName());
                        }
                    }
                    if (llenadoHistoria) {
                        antecedentesStr = ingresarInfo(antecedentesStr,"antecendentesMedicos", "");
                    } else {
                        antecedentesStr = eliminarInfo(antecedentesStr, "antecendentesMedicos");
                    }
                    if(llenadoHistoria || llenadosMedicamentos) {
                        llenadoAntecedentes = true;
                    }
                    break;
                }
            }

            if (llenadoAntecedentes) {
                antecedentesStr = ingresarInfo(antecedentesStr,"antecedentesCompletos", "");
            } else {
                antecedentesStr = eliminarInfo(antecedentesStr, "antecedentesCompletos");
            }

            if(medico != null) {
                if(medico.getTelefono() != null) {
                    antecedentesStr = antecedentesStr.replace("@medicoTelefono", medico.getTelefono());
                }else{
                    antecedentesStr = antecedentesStr.replace("@medicoTelefono", "");
                }

                antecedentesStr = antecedentesStr.replace("@medicoCorreo", medico.getEmail());

                if(medico.getTelefono() != null) {
                    antecedentesStr = antecedentesStr.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
                }else{
                    antecedentesStr = antecedentesStr.replace("@medicoNombre", "");
                }
            } else {
                antecedentesStr = antecedentesStr.replace("@medicoTelefono", "");
                antecedentesStr = antecedentesStr.replace("@medicoCorreo", "");
                antecedentesStr = antecedentesStr.replace("@medicoNombre", "");
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// pasarintro2
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        resultadoImpresion += antecedentesStr;

        String template = templateInicial;

        Object someObject2 = historiaInicial;

        Cita cita = historiaInicial.getCita();

        User medico = cita.getEspecialidadMedico().getMedico();

        String nombre = null;
        String fechaNacimiento = null;
        String edad = null;
        String cedula = null;
        String sexo = null;
        String email = null;
        String telefono = null;
        if(cita.isPacienteNino()) {
            for(HistoriaPersonalNino historiaPersonal: historiaPersonalNinoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null)
                    sexo = historiaPersonal.getSexo().getNombre();
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                } else if (historiaPersonal.getTelefonoCelularRepresentante() != null) {
                    telefono = historiaPersonal.getTelefonoCelularRepresentante();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        } else {
            for(HistoriaPersonalAdulto historiaPersonal: historiaPersonalAdultoRepository.findByPaciente_Id(cita.getPaciente().getId())) {
                nombre = historiaPersonal.getPrimerNombre() + " " + historiaPersonal.getPrimerApellido();
                if(historiaPersonal.getFechaNacimiento() != null) {
                    fechaNacimiento = historiaPersonal.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));;
                    edad = calculateAge(historiaPersonal.getFechaNacimiento());
                }
                cedula = historiaPersonal.getCedula();
                if(historiaPersonal.getSexo() != null)
                    sexo = historiaPersonal.getSexo().getNombre();
                if(historiaPersonal.getTelefonoCelular() != null) {
                    telefono = historiaPersonal.getTelefonoCelular();
                }
                if(historiaPersonal.getEmail() != null) {
                    email = historiaPersonal.getEmail();
                }
                break;
            }
        }

        if(medico != null) {
            if(medico.getTelefono() != null) {
                template = template.replace("@medicoTelefono", medico.getTelefono());
            }else{
                template = template.replace("@medicoTelefono", "");
            }

            template = template.replace("@medicoCorreo", medico.getEmail());

            if(medico.getTelefono() != null) {
                template = template.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
            }else{
                template = template.replace("@medicoNombre", "");
            }
        } else {
            template = template.replace("@medicoTelefono", "");
            template = template.replace("@medicoCorreo", "");
            template = template.replace("@medicoNombre", "");
        }

        if(cita.getFecha() != null)
            template = template.replace("@citaFecha", getFecha(cita.getFecha()));
        else
            template = template.replace("@citaFecha", "");

        if(nombre != null)
            template = template.replace("@pacienteNombre", nombre);
        else
            template = template.replace("@pacienteNombre", "");
        if(fechaNacimiento != null)
            template = template.replace("@pacienteFechaNacimiento", fechaNacimiento);
        else
            template = template.replace("@pacienteFechaNacimiento", "");
        if(edad != null)
            template = template.replace("@pacienteEdad", edad);
        else
            template = template.replace("@pacienteEdad", "");
        if(cedula != null)
            template = template.replace("@pacienteCedula", cedula);
        else
            template = template.replace("@pacienteCedula", "");
        if(sexo != null)
            template = template.replace("@pacienteSexo", sexo);
        else
            template = template.replace("@pacienteSexo", "");
        if(telefono != null)
            template = template.replace("@pacienteTelefono", telefono);
        else
            template = template.replace("@pacienteTelefono", "");
        if(email != null)
            template = template.replace("@pacienteEmail", email);
        else
            template = template.replace("@pacienteEmail", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        template = template.replace("@fechaActual", getFecha(LocalDate.now()));

        if(historiaInicial.getFecha() != null){
            template = template.replace("@fecha", getFechaSinHora(historiaInicial.getFecha()));
            template = template.replace("@hora", getHora(historiaInicial.getFecha()));
        } else {
            template = template.replace("@fecha", "");
            template = template.replace("@hora", "");
        }

        if(cita.getPaciente().getNumeroHistoria() != null)
            template = template.replace("@pacienteNumeroHistoria", cita.getPaciente().getNumeroHistoria().toString());
        else
            template = template.replace("@pacienteNumeroHistoria", "");

        if(historiaInicial.getNuevosSintomas() != null)
            template = template.replace("@nuevosSintomas", historiaInicial.getNuevosSintomas());
        else
            template = template.replace("@nuevosSintomas", "");

        if(historiaInicial.getRecomendacionesGenerales() != null)
            template = ingresarInfo(template, "recomendacionesGenerales", historiaInicial.getRecomendacionesGenerales());
        else
            template = eliminarInfo(template, "recomendacionesGenerales");

        if(historiaInicial.getRecomendaciones() != null)
            template = ingresarInfo(template, "recomendaciones", historiaInicial.getRecomendaciones());
        else
            template = eliminarInfo(template, "recomendaciones");

        historiaInicial.setExamenesComplementarios(examenComplementarioRepository.findByEvolucionPaciente_Id(historiaInicial.getId()));
        String examenesComplementarios = "";
        if(historiaInicial.getExamenesComplementarios() != null) {
            for(ExamenComplementario ex : historiaInicial.getExamenesComplementarios()) {
                if (ex.getArchivoContentType() != null) {
                    String descripcion = ex.getDescripcion();
                    if(descripcion == null){
                        descripcion = "";
                    }
                    if (ex.getArchivoContentType().contains("image")) {
                        examenesComplementarios += descripcion + "<br/><br/>" + getImagen(ex) + "<br/>";
                    } else {
                        examenesComplementarios += "<a href=\"http://innap.net/citas/?examen-complementario/" + ex.getId() + "/view\">" + descripcion + "</a>" + "<br/>";
                    }
                }
            }
        }

        if(!examenesComplementarios.equalsIgnoreCase(""))
            template = ingresarInfo(template, "examenesComplementarios", examenesComplementarios);
        else
            template = eliminarInfo(template, "examenesComplementarios");

        if (historiaInicial.getTratamiento() != null) {
            Gson gson = new Gson();

            Tratamiento[] tratamientoArray = gson.fromJson(historiaInicial.getTratamiento(), Tratamiento[].class);

            String tratamientos = "";
            Boolean llenadoTratamiento = false;
            for(Tratamiento tratamiento : tratamientoArray) {
                if (tratamiento.getNombre() != null) {
                    llenadoTratamiento = true;
                    if (tratamiento.getIndicacion() == null) {
                        tratamientos += tratamiento.getNombre() + "<br/>";
                    } else {
                        tratamientos += tratamiento.getNombre() + ": " + tratamiento.getIndicacion() + "<br/>";
                    }
                }

            }
            if(llenadoTratamiento) {
                template = ingresarInfo(template, "tratamiento", tratamientos);
            } else {
                template = eliminarInfo(template, "tratamiento");
            }

        } else {
            template = eliminarInfo(template, "tratamiento");
        }

        if (historiaInicial.getExamenesIndicados() != null) {
            Gson gson = new Gson();

            ExamenIndicado[] examenIndicadoArray = gson.fromJson(historiaInicial.getExamenesIndicados(), ExamenIndicado[].class);

            String examenes = "";
            Boolean llenadoExamenes = false;
            for(ExamenIndicado examenIndicado : examenIndicadoArray) {
                if (examenIndicado.getExamen() != null) {
                    llenadoExamenes = true;
                    if (examenIndicado.getReferencia() == null) {
                        examenes += examenIndicado.getExamen() + "<br/>";
                    } else {
                        examenes += examenIndicado.getExamen() + ": " + examenIndicado.getReferencia() + "<br/>";
                    }
                }
            }
            if(llenadoExamenes) {
                template = ingresarInfo(template, "examenesIndicados", examenes);
            } else {
                template = eliminarInfo(template, "examenesIndicados");
            }

        } else {
            template = eliminarInfo(template, "examenesIndicados");
        }

        String consultaOnlineText = "";
        if (historiaInicial.getCita().getConsultaOnline() != null && historiaInicial.getCita().getConsultaOnline()) {
            consultaOnlineText = "Online";
        } else {
            consultaOnlineText = "Presencial";
        }
        template = template.replace("@consultaOnline",consultaOnlineText);

        if(historiaInicial.getCita().getTipoConsulta() != null)
            template = template.replace("@tipoConsulta", historiaInicial.getCita().getTipoConsulta().getNombre());
        else
            template = template.replace("@tipoConsulta", "");


        for (Field field : someObject2.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(someObject2);
            if (value != null) {
                template = ingresarInfo(template, field.getName(), value.toString());
            } else {
                template = eliminarInfo(template, field.getName());
            }
        }

        template = template.replace("@selloMedico", getFirma(historiaInicial.getCita().getEspecialidadMedico().getMedico()));

        if (historiaInicial.getFecha() == null && historiaInicial.getPeso() == null && historiaInicial.gettAAcostada() == null &&
            historiaInicial.gettASentada() == null && historiaInicial.gettAParada() == null && historiaInicial.getTalla() == null &&
            historiaInicial.getImc() == null && historiaInicial.getFirma() == null) {
            template = eliminarInfo(template, "signosVitales");
        } else {
            template = ingresarInfo(template, "signosVitales", "");
        }

        if (historiaInicial.getLateralidadMano() == null && historiaInicial.getLateralidadOido() == null && historiaInicial.getLateralidadOjo() == null &&
            historiaInicial.getLateralidadPierna() == null) {
            template = eliminarInfo(template, "lateralidad");
        } else {
            template = ingresarInfo(template, "lateralidad", "");
        }

        resultadoImpresion += template;

        List<PieDePaginaInforme> pies = pieDePaginaInformeRepository.findAll();
        String footer = "";
        if(pies.size() > 0) {

            PieDePaginaInforme pie = pies.get(0);

            footer += pie.getPieDePagina();

            cita = historiaInicial.getCita();
            medico = cita.getEspecialidadMedico().getMedico();

            if(medico != null) {
                if(medico.getTelefono() != null) {
                    footer = footer.replace("@medicoTelefono", medico.getTelefono());
                }else{
                    footer = footer.replace("@medicoTelefono", "");
                }

                footer = footer.replace("@medicoCorreo", medico.getEmail());

                if(medico.getTelefono() != null) {
                    footer = footer.replace("@medicoNombre", medico.getFirstName() + " " + medico.getLastName());
                }else{
                    footer = footer.replace("@medicoNombre", "");
                }
            } else {
                footer = footer.replace("@medicoTelefono", "");
                footer = footer.replace("@medicoCorreo", "");
                footer = footer.replace("@medicoNombre", "");
            }

            footer = footer.replace("@selloMedico", getFirma(historiaInicial.getCita().getEspecialidadMedico().getMedico()));
        }

        String resultado = generarPdfFromHtml(resultadoImpresion, header, footer);

        return resultado;
    }

    public String generarPdfFromHtml(String str, String header, String footer) throws IOException, DocumentException {

        header = "<style>" +
            "@font-face {" +
            "        font-family: 'HelveticaNeueLTPro-Lt';" +
            "      }" +
            "*"+
            "    {"+
            "        font-size: 10pt;"+
            "        font-family: HelveticaNeueLTPro-Lt !important;"+
            "}"+
            "</style>" +
            header;

        footer = "<style>" +
            "@font-face {" +
            "        font-family: 'HelveticaNeueLTPro-Lt';" +
            "      }" +
            "*"+
            "    {"+
            "        font-size: 10pt;"+
            "        font-family: HelveticaNeueLTPro-Lt !important;"+
            "}"+
            "</style>" +
            footer;

        str = "<style>" +
            "@font-face {" +
            "        font-family: 'HelveticaNeueLTPro-Lt';" +
            "        font-family: 'HelveticaNeueLTPro-Ex';" +
            "      }" +
            "*"+
            "    {"+
            "        font-size: 10pt;"+
            "        font-family: HelveticaNeueLTPro-Lt !important;"+
            "}"+
            "</style>" +
            str;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // step 1
        float left = 30;
        float right = 30;
        float top = 115;
        float bottom = 100;
        Document document = new Document(PageSize.A4, left, right, top, bottom);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        // step 3
        document.open();
        // step 4
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(header, footer);
            writer.setPageEvent(event);

        // CSS
        CSSResolver cssResolver =
            XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

        // HTML
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register("HelveticaNeueLTPro-Ex.otf");
        fontProvider.register("HelveticaNeueLTPro-Lt.otf");
        fontProvider.addFontSubstitute("Arial", "HelveticaNeueLTPro-Lt");
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new Base64ImageProvider());

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);

        event.onStartPage(writer, document);

        p.parse(new ByteArrayInputStream(str.getBytes()));

        document.close();

        return Base64.encodeBytes(byteArrayOutputStream.toByteArray());
    }

    public String generarPdfFromHtmlChiquitico(String str) throws IOException, DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // step 1
        Rectangle mediabox = new Rectangle(getHalfPageSize(PageSize.A4));
        Document document = new Document(PageSize.A4.rotate(), 25, mediabox.rotate().getRight() + 25, 25, 25);;
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        // step 3
        document.open();
        // step 4

        // CSS
        CSSResolver cssResolver =
            XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new Base64ImageProvider());

        // Pipelines
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

        // XML Worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new ByteArrayInputStream(str.getBytes()));

        document.close();

        return Base64.encodeBytes(byteArrayOutputStream.toByteArray());
    }

    public Rectangle getHalfPageSize(Rectangle pagesize) {
        float width = pagesize.getWidth();
        float height = pagesize.getHeight();
        return new Rectangle(width, height / 2);
    }

    public String calculateAge(LocalDate birthDate) {
        return getAge(birthDate);
    }

    public String getImagen(ExamenComplementario iten){
        String firma = "";
        if(iten.getArchivo() != null) {
            String archivo = Base64.encodeBytes(iten.getArchivo());
            firma = "<img style=\"margin-top: 10px;\" src=\"data:" + iten.getArchivoContentType() + ";base64," + archivo + "\" width=\"200\"/>";
        }

        return firma;
    }

    public String getFirma(User user){
        Perfil perfil = perfilRepository.findByUser_Id(user.getId());
        String firma = "";
        if(perfil.getSello() != null) {
            String archivo = Base64.encodeBytes(perfil.getSello());
            firma = "<img style=\"margin-top: 10px;\" src=\"data:" + perfil.getSelloContentType() + ";base64," + archivo + "\" width=\"200\"/>";
        }

        return firma;
    }

    class Base64ImageProvider extends AbstractImageProvider {

        @Override
        public com.itextpdf.text.Image retrieve(String src) {
            int pos = src.indexOf("base64,");
            try {
                if (src.startsWith("data") && pos > 0) {
                    byte[] img = Base64.decode(src.substring(pos + 7));
                    return com.itextpdf.text.Image.getInstance(img);
                }
                else {
                    try {
                        return com.itextpdf.text.Image.getInstance(src);
                    } catch (BadElementException e) {
                        e.printStackTrace();
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            } catch (BadElementException ex) {
                return null;
            } catch (IOException ex) {
                return null;
            }
        }

        @Override
        public String getImageRootPath() {
            return null;
        }
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

    String getFechaSinHora(ZonedDateTime localDate) {
        String fecha = "";

        String[] meses = {"Ene.","Feb.","Mar.","Abr.","May.","Jun.","Jul.","Ago.","Sep.","Oct.","Nov.","Dic."};

        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMes = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterAno = DateTimeFormatter.ofPattern("yyyy");
        fecha += localDate.minusHours(4L).format(formatterDia);
        fecha += " " + meses[Integer.parseInt(localDate.minusHours(4L).format(formatterMes)) - 1];
        fecha += " " + localDate.minusHours(4L).format(formatterAno);

        return fecha;
    }

    String getHora(ZonedDateTime localDate) {
        String fecha = "";
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm a");
        fecha += " " + localDate.minusHours(4L).format(formatterHour);

        return fecha;
    }

    private String getAge(LocalDate localDate)
    {
        if(localDate == null)
            return "";

        LocalDate now = LocalDate.now();

        int anos = Period.between(localDate, now).getYears();
        String edad = "";
        if(anos <= 0)
        {
            anos = Period.between(localDate, now).getMonths();
            if(anos <= 0)
            {
                anos = Period.between(localDate, now).getDays();
                edad = anos + " días";
            }else{
                edad = anos + " meses";
            }
        }else{
            if(anos == 1)
                edad = anos + " año";
            else
                edad = anos + " años";

            anos = Period.between(localDate.withYear(now.getYear() - 1), now).getMonths();
            edad += " y " + anos + " meses";
        }

        return edad;
    }

    /**
     * DELETE  /evolucion-pacientes/:id : delete the "id" evolucionPaciente.
     *
     * @param id the id of the evolucionPaciente to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evolucion-pacientes/{id}")
    public ResponseEntity<Void> deleteEvolucionPaciente(@PathVariable Long id) {
        log.debug("REST request to delete EvolucionPaciente : {}", id);
        evolucionPacienteRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public class HeaderFooterPageEvent extends PdfPageEventHelper {

        protected ElementList header;
        protected ElementList footer;

        HeaderFooterPageEvent(String header, String footer) {
            try {
                this.header = XMLWorkerHelper.parseToElementList(header, null);
                this.footer = XMLWorkerHelper.parseToElementList(footer, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void onStartPage(PdfWriter writer, Document document) {
            try {

                Rectangle pageSize = document.getPageSize();

                float left = document.leftMargin();
                float right = document.rightMargin();
                float top = document.topMargin();
                float bottom = document.bottomMargin();

                Rectangle rect1 = new Rectangle(
                    pageSize.getLeft() + left, pageSize.getTop() - top,
                    pageSize.getRight() - right, pageSize.getTop() - 15);

                ColumnText ct = new ColumnText(writer.getDirectContent());

                ct.setSimpleColumn(rect1);

                for (Element e : header) {

                    ct.addElement(e);

                }

                ct.go();

            } catch (DocumentException de) {

                throw new ExceptionConverter(de);

            }
        }

        public void onEndPage(PdfWriter writer, Document document) {
            try {

                Rectangle pageSize = document.getPageSize();

                float left = document.leftMargin();
                float right = document.rightMargin();
                float top = document.topMargin();
                float bottom = document.bottomMargin();

                Rectangle rect2 = new Rectangle(
                    pageSize.getLeft(), pageSize.getBottom(),
                    pageSize.getRight(), pageSize.getBottom() + bottom - 25);

                ColumnText ct = new ColumnText(writer.getDirectContent());

                ct.setSimpleColumn(rect2);

                for (Element e : footer) {

                    ct.addElement(e);

                }

                ct.go();

            } catch (DocumentException de) {

                throw new ExceptionConverter(de);

            }
        }

    }

    public class DefaultFontProvider extends FontFactoryImp {
        private String def;
        public DefaultFontProvider(String def) {
            def = def;
        }

        // I believe this is the correct override, but there are quite a few others.
        public Font getFont(String fontName, String encoding, boolean embedded, float size, int style, BaseColor color, boolean cached) {
            if (fontName == null || fontName.length() == 0) {
                fontName = def;
            }
            return super.getFont(fontName, encoding, embedded, 8, style, color, cached);
        }
    }
}



