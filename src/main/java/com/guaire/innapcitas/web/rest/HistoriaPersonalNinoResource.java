package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.HistoriaMedicaAdulto;
import com.guaire.innapcitas.domain.HistoriaMedicaNino;
import com.guaire.innapcitas.domain.HistoriaPersonalNino;
import com.guaire.innapcitas.domain.User;
import com.guaire.innapcitas.repository.HistoriaMedicaNinoRepository;
import com.guaire.innapcitas.repository.HistoriaPersonalNinoRepository;
import com.guaire.innapcitas.repository.PlantillaHistoriaPersonalAdultoRepository;
import com.guaire.innapcitas.repository.PlantillaHistoriaPersonalNinoRepository;
import com.guaire.innapcitas.web.rest.errors.BadRequestAlertException;
import com.guaire.innapcitas.web.rest.util.HeaderUtil;
import com.guaire.innapcitas.web.rest.util.PaginationUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing HistoriaPersonalNino.
 */
@RestController
@RequestMapping("/api")
public class HistoriaPersonalNinoResource {

    private final Logger log = LoggerFactory.getLogger(HistoriaPersonalNinoResource.class);

    private static final String ENTITY_NAME = "historiaPersonalNino";

    private final HistoriaPersonalNinoRepository historiaPersonalNinoRepository;

    private final HistoriaMedicaNinoRepository historiaMedicaNinoRepository;

    private final PlantillaHistoriaPersonalNinoRepository plantillaHistoriaPersonalNinoRepository;

    public HistoriaPersonalNinoResource(HistoriaPersonalNinoRepository historiaPersonalNinoRepository, PlantillaHistoriaPersonalNinoRepository plantillaHistoriaPersonalNinoRepository,
                                        HistoriaMedicaNinoRepository historiaMedicaNinoRepository) {
        this.historiaPersonalNinoRepository = historiaPersonalNinoRepository;
        this.plantillaHistoriaPersonalNinoRepository = plantillaHistoriaPersonalNinoRepository;
        this.historiaMedicaNinoRepository =  historiaMedicaNinoRepository;
    }

    /**
     * POST  /historia-personal-ninos : Create a new historiaPersonalNino.
     *
     * @param historiaPersonalNino the historiaPersonalNino to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historiaPersonalNino, or with status 400 (Bad Request) if the historiaPersonalNino has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/historia-personal-ninos")
    public ResponseEntity<HistoriaPersonalNino> createHistoriaPersonalNino(@Valid @RequestBody HistoriaPersonalNino historiaPersonalNino) throws URISyntaxException {
        log.debug("REST request to save HistoriaPersonalNino : {}", historiaPersonalNino);
        if (historiaPersonalNino.getId() != null) {
            throw new BadRequestAlertException("A new historiaPersonalNino cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriaPersonalNino result = historiaPersonalNinoRepository.save(historiaPersonalNino);
        return ResponseEntity.created(new URI("/api/historia-personal-ninos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /historia-personal-ninos : Updates an existing historiaPersonalNino.
     *
     * @param historiaPersonalNino the historiaPersonalNino to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historiaPersonalNino,
     * or with status 400 (Bad Request) if the historiaPersonalNino is not valid,
     * or with status 500 (Internal Server Error) if the historiaPersonalNino couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/historia-personal-ninos")
    public ResponseEntity<HistoriaPersonalNino> updateHistoriaPersonalNino(@Valid @RequestBody HistoriaPersonalNino historiaPersonalNino) throws URISyntaxException {
        log.debug("REST request to update HistoriaPersonalNino : {}", historiaPersonalNino);
        if (historiaPersonalNino.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriaPersonalNino result = historiaPersonalNinoRepository.save(historiaPersonalNino);

        Set<HistoriaMedicaNino> historiaMedicaNinos = historiaMedicaNinoRepository.findByPaciente_Id(result.getPaciente().getId());
        for(HistoriaMedicaNino histMed: historiaMedicaNinos) {
            histMed.setPrimerNombre(result.getPrimerNombre());
            histMed.setPrimerApellido(result.getPrimerApellido());
            histMed.setEmail(result.getEmail());
            histMed.setCedula(result.getCedula());
            historiaMedicaNinoRepository.save(histMed);
            break;
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historiaPersonalNino.getId().toString()))
            .body(result);
    }

    /**
     * GET  /historia-personal-ninos : get all the historiaPersonalNinos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of historiaPersonalNinos in body
     */
    @GetMapping("/historia-personal-ninos")
    public ResponseEntity<List<HistoriaPersonalNino>> getAllHistoriaPersonalNinos(Pageable pageable) {
        log.debug("REST request to get a page of HistoriaPersonalNinos");
        Page<HistoriaPersonalNino> page = historiaPersonalNinoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/historia-personal-ninos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /historia-personal-ninos/:id : get the "id" historiaPersonalNino.
     *
     * @param id the id of the historiaPersonalNino to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaPersonalNino, or with status 404 (Not Found)
     */
    @GetMapping("/historia-personal-ninos/{id}")
    public ResponseEntity<HistoriaPersonalNino> getHistoriaPersonalNino(@PathVariable Long id) {
        log.debug("REST request to get HistoriaPersonalNino : {}", id);
        Optional<HistoriaPersonalNino> historiaPersonalNino = historiaPersonalNinoRepository.findById(id);

        Set<HistoriaMedicaNino> historiaMedicaAdultos = historiaMedicaNinoRepository.findByPaciente_Id(historiaPersonalNino.get().getPaciente().getId());
        for(HistoriaMedicaNino histMed: historiaMedicaAdultos) {
            historiaPersonalNino.get().setHistoriaMedicaId(histMed.getId());
            break;
        }

        return ResponseUtil.wrapOrNotFound(historiaPersonalNino);
    }

    /**
     * DELETE  /historia-personal-ninos/:id : delete the "id" historiaPersonalNino.
     *
     * @param id the id of the historiaPersonalNino to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/historia-personal-ninos/{id}")
    public ResponseEntity<Void> deleteHistoriaPersonalNino(@PathVariable Long id) {
        log.debug("REST request to delete HistoriaPersonalNino : {}", id);
        historiaPersonalNinoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /historia-personal-ninos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/historia-personal-ninos/file/{id}")
    public ResponseEntity<HistoriaPersonalNino> getHistoriaPersonalNinoFile(@PathVariable Long id) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<HistoriaPersonalNino> historia = historiaPersonalNinoRepository.findById(id);

        User paciente = historia.get().getPaciente();

        String template = plantillaHistoriaPersonalNinoRepository.findAll().get(0).getContenido();

        if(paciente.getNumeroHistoria() != null)
            template = template.replace("@pacienteNumeroHistoria", paciente.getNumeroHistoria().toString());
        else
            template = template.replace("@pacienteNumeroHistoria", " - ");

        if(historia.get().getSexo() != null)
            template = template.replace("@sexo", historia.get().getSexo().getNombre());
        else
            template = template.replace("@sexo", " - ");

        template = template.replace("@fechaActual", getFecha(LocalDate.now()));

        if(historia.get().getTipoConsulta() != null)
            template = template.replace("@tipoConsulta", historia.get().getTipoConsulta().getNombre());
        else
            template = template.replace("@tipoConsulta", " - ");

        if(historia.get().getEstadoCivil() != null)
            template = template.replace("@estadoCivil", historia.get().getEstadoCivil().getNombre());
        else
            template = template.replace("@estadoCivil", " - ");

        if(historia.get().getEspecialidadFamiliarMedico() != null)
            template = template.replace("@especialidadFamiliarMedico", historia.get().getEspecialidadFamiliarMedico());
        else
            template = template.replace("@especialidadFamiliarMedico", " - ");

        if(historia.get().getReferidoPor() != null)
            template = template.replace("@referidoPor", historia.get().getReferidoPor().getNombre());
        else
            template = template.replace("@referidoPor", " - ");

        if(historia.get().getCedulaRepresentante() != null)
            template = template.replace("@cedulaRepresentante", historia.get().getCedulaRepresentante());
        else
            template = template.replace("@cedulaRepresentante", " - ");

        if(historia.get().getEdadRepresentante() != null)
            template = template.replace("@edadRepresentante", historia.get().getEdadRepresentante().toString());
        else
            template = template.replace("@edadRepresentante", " - ");

        if(historia.get().getTelefonoCelularRepresentante() != null)
            template = template.replace("@telefonoCelularRepresentante", historia.get().getTelefonoCelularRepresentante());
        else
            template = template.replace("@telefonoCelularRepresentante", " - ");

        if(historia.get().getGradoInstruccionMadre() != null)
            template = template.replace("@gradoInstruccionMadre", historia.get().getGradoInstruccionMadre().getNombre());
        else
            template = template.replace("@gradoInstruccionMadre", " - ");

        if(historia.get().getGradoInstruccionPadre() != null)
            template = template.replace("@gradoInstruccionPadre", historia.get().getGradoInstruccionPadre().getNombre());
        else
            template = template.replace("@gradoInstruccionPadre", " - ");

        if(historia.get().getGradoInstruccionOtro() != null)
            template = template.replace("@gradoInstruccionOtro", historia.get().getGradoInstruccionOtro().getNombre());
        else
            template = template.replace("@gradoInstruccionOtro", " - ");

        if(historia.get().getEspecialidad() != null)
            template = template.replace("@especialidad", historia.get().getEspecialidad().getEspecialidad());
        else
            template = template.replace("@especialidad", " - ");

        if(historia.get().getFechaNacimiento() != null)
            template = template.replace("@edad", getAge(historia.get().getFechaNacimiento()));
        else
            template = template.replace("@edad", " - ");

        String file = getPdf(historia.get(), template);
        historia.get().setArchivo(file);

        return ResponseUtil.wrapOrNotFound(historia);
    }

    public String getPdf(HistoriaPersonalNino historia, String template) throws IOException, DocumentException, IllegalAccessException {

        Object someObject = historia;
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
                template = template.replace("@" + field.getName(), value.toString());
            } else {
                String vacio = " - ";
                if(field.getType().equals(Boolean.class)) {
                    vacio = "No";
                }
                template = template.replace("@" + field.getName(), vacio);
            }
        }

        String resultado = generarPdfFromHtml(template);

        return resultado;
    }

    public String generarPdfFromHtml(String str) throws IOException, DocumentException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // step 1
        Document document = new Document();
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
}
