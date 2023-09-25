package com.guaire.innapcitas.web.rest;
import com.guaire.innapcitas.domain.HistoriaMedicaAdulto;
import com.guaire.innapcitas.domain.HistoriaPersonalAdulto;
import com.guaire.innapcitas.domain.User;
import com.guaire.innapcitas.repository.HistoriaMedicaAdultoRepository;
import com.guaire.innapcitas.repository.HistoriaPersonalAdultoRepository;
import com.guaire.innapcitas.repository.PlantillaHistoriaMedicaNinoRepository;
import com.guaire.innapcitas.repository.PlantillaHistoriaPersonalAdultoRepository;
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
 * REST controller for managing HistoriaPersonalAdulto.
 */
@RestController
@RequestMapping("/api")
public class HistoriaPersonalAdultoResource {

    private final Logger log = LoggerFactory.getLogger(HistoriaPersonalAdultoResource.class);

    private static final String ENTITY_NAME = "historiaPersonalAdulto";

    private final HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository;

    private final HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository;

    private final PlantillaHistoriaPersonalAdultoRepository plantillaHistoriaPersonalAdultoRepository;

    public HistoriaPersonalAdultoResource(HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository, PlantillaHistoriaPersonalAdultoRepository plantillaHistoriaPersonalAdultoRepository,
                                          HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository) {
        this.historiaPersonalAdultoRepository = historiaPersonalAdultoRepository;
        this.plantillaHistoriaPersonalAdultoRepository = plantillaHistoriaPersonalAdultoRepository;
        this.historiaMedicaAdultoRepository = historiaMedicaAdultoRepository;
    }

    /**
     * POST  /historia-personal-adultos : Create a new historiaPersonalAdulto.
     *
     * @param historiaPersonalAdulto the historiaPersonalAdulto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historiaPersonalAdulto, or with status 400 (Bad Request) if the historiaPersonalAdulto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/historia-personal-adultos")
    public ResponseEntity<HistoriaPersonalAdulto> createHistoriaPersonalAdulto(@Valid @RequestBody HistoriaPersonalAdulto historiaPersonalAdulto) throws URISyntaxException {
        log.debug("REST request to save HistoriaPersonalAdulto : {}", historiaPersonalAdulto);
        if (historiaPersonalAdulto.getId() != null) {
            throw new BadRequestAlertException("A new historiaPersonalAdulto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriaPersonalAdulto result = historiaPersonalAdultoRepository.save(historiaPersonalAdulto);

        return ResponseEntity.created(new URI("/api/historia-personal-adultos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /historia-personal-adultos : Updates an existing historiaPersonalAdulto.
     *
     * @param historiaPersonalAdulto the historiaPersonalAdulto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historiaPersonalAdulto,
     * or with status 400 (Bad Request) if the historiaPersonalAdulto is not valid,
     * or with status 500 (Internal Server Error) if the historiaPersonalAdulto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/historia-personal-adultos")
    public ResponseEntity<HistoriaPersonalAdulto> updateHistoriaPersonalAdulto(@Valid @RequestBody HistoriaPersonalAdulto historiaPersonalAdulto) throws URISyntaxException {
        log.debug("REST request to update HistoriaPersonalAdulto : {}", historiaPersonalAdulto);
        if (historiaPersonalAdulto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriaPersonalAdulto result = historiaPersonalAdultoRepository.save(historiaPersonalAdulto);

        Set<HistoriaMedicaAdulto> historiaMedicaAdultos = historiaMedicaAdultoRepository.findByPaciente_Id(result.getPaciente().getId());
        for(HistoriaMedicaAdulto histMed: historiaMedicaAdultos) {
            histMed.setPrimerNombre(result.getPrimerNombre());
            histMed.setPrimerApellido(result.getPrimerApellido());
            histMed.setEmail(result.getEmail());
            histMed.setCedula(result.getCedula());
            historiaMedicaAdultoRepository.save(histMed);
            break;
        }

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historiaPersonalAdulto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /historia-personal-adultos : get all the historiaPersonalAdultos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of historiaPersonalAdultos in body
     */
    @GetMapping("/historia-personal-adultos")
    public ResponseEntity<List<HistoriaPersonalAdulto>> getAllHistoriaPersonalAdultos(Pageable pageable) {
        log.debug("REST request to get a page of HistoriaPersonalAdultos");
        Page<HistoriaPersonalAdulto> page = historiaPersonalAdultoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/historia-personal-adultos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /historia-personal-adultos/:id : get the "id" historiaPersonalAdulto.
     *
     * @param id the id of the historiaPersonalAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaPersonalAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/historia-personal-adultos/{id}")
    public ResponseEntity<HistoriaPersonalAdulto> getHistoriaPersonalAdulto(@PathVariable Long id) {
        log.debug("REST request to get HistoriaPersonalAdulto : {}", id);
        Optional<HistoriaPersonalAdulto> historiaPersonalAdulto = historiaPersonalAdultoRepository.findById(id);

        historiaMedicaAdultoRepository.findByPaciente_Id(historiaPersonalAdulto.get().getPaciente().getId());

        Set<HistoriaMedicaAdulto> historiaMedicaAdultos = historiaMedicaAdultoRepository.findByPaciente_Id(historiaPersonalAdulto.get().getPaciente().getId());
        for(HistoriaMedicaAdulto histMed: historiaMedicaAdultos) {
            historiaPersonalAdulto.get().setHistoriaMedicaId(histMed.getId());
            break;
        }

        return ResponseUtil.wrapOrNotFound(historiaPersonalAdulto);
    }

    /**
     * DELETE  /historia-personal-adultos/:id : delete the "id" historiaPersonalAdulto.
     *
     * @param id the id of the historiaPersonalAdulto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/historia-personal-adultos/{id}")
    public ResponseEntity<Void> deleteHistoriaPersonalAdulto(@PathVariable Long id) {
        log.debug("REST request to delete HistoriaPersonalAdulto : {}", id);
        historiaPersonalAdultoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /historia-personal-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/historia-personal-adultos/file/{id}")
    public ResponseEntity<HistoriaPersonalAdulto> getHistoriaPersonalAdultoFile(@PathVariable Long id) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<HistoriaPersonalAdulto> historia = historiaPersonalAdultoRepository.findById(id);

        User paciente = historia.get().getPaciente();

        String template = plantillaHistoriaPersonalAdultoRepository.findAll().get(0).getContenido();

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

    public String getPdf(HistoriaPersonalAdulto historia, String template) throws IOException, DocumentException, IllegalAccessException {

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
