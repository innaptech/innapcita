package com.guaire.innapcitas.web.rest;
import com.google.gson.Gson;
import com.guaire.innapcitas.domain.*;
import com.guaire.innapcitas.repository.EvolucionPacienteRepository;
import com.guaire.innapcitas.repository.HistoriaMedicaAdultoRepository;
import com.guaire.innapcitas.repository.HistoriaPersonalAdultoRepository;
import com.guaire.innapcitas.repository.PlantillaHistoriaMedicaAdultoRepository;
import com.guaire.innapcitas.web.rest.errors.BadRequestAlertException;
import com.guaire.innapcitas.web.rest.util.HeaderUtil;
import com.guaire.innapcitas.web.rest.util.PaginationUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.pdf.StringUtils;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.html.Image;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.pdf.codec.Base64;
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

/**
 * REST controller for managing HistoriaMedicaAdulto.
 */
@RestController
@RequestMapping("/api")
public class HistoriaMedicaAdultoResource {

    private final Logger log = LoggerFactory.getLogger(HistoriaMedicaAdultoResource.class);

    private static final String ENTITY_NAME = "historiaMedicaAdulto";

    private final HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository;

    private final PlantillaHistoriaMedicaAdultoRepository plantillaHistoriaMedicaAdultoRepository;

    private final HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository;

    private final EvolucionPacienteRepository evolucionPacienteRepository;

    public HistoriaMedicaAdultoResource(HistoriaMedicaAdultoRepository historiaMedicaAdultoRepository, PlantillaHistoriaMedicaAdultoRepository plantillaHistoriaMedicaAdultoRepository,
                                        HistoriaPersonalAdultoRepository historiaPersonalAdultoRepository, EvolucionPacienteRepository evolucionPacienteRepository) {
        this.historiaMedicaAdultoRepository = historiaMedicaAdultoRepository;
        this.plantillaHistoriaMedicaAdultoRepository = plantillaHistoriaMedicaAdultoRepository;
        this.historiaPersonalAdultoRepository = historiaPersonalAdultoRepository;
        this.evolucionPacienteRepository = evolucionPacienteRepository;
    }

    /**
     * POST  /historia-medica-adultos : Create a new historiaMedicaAdulto.
     *
     * @param historiaMedicaAdulto the historiaMedicaAdulto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new historiaMedicaAdulto, or with status 400 (Bad Request) if the historiaMedicaAdulto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/historia-medica-adultos")
    public ResponseEntity<HistoriaMedicaAdulto> createHistoriaMedicaAdulto(@Valid @RequestBody HistoriaMedicaAdulto historiaMedicaAdulto) throws URISyntaxException {
        log.debug("REST request to save HistoriaMedicaAdulto : {}", historiaMedicaAdulto);
        if (historiaMedicaAdulto.getId() != null) {
            throw new BadRequestAlertException("A new historiaMedicaAdulto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoriaMedicaAdulto result = historiaMedicaAdultoRepository.save(historiaMedicaAdulto);

        HistoriaPersonalAdulto historiaPersonal = historiaPersonalAdultoRepository.getByPaciente_Id(result.getPaciente().getId()).get(0);
        if((historiaPersonal.getPrimerNombre() == null) && (result.getPrimerNombre() != null)) {
            historiaPersonal.setPrimerNombre(result.getPrimerNombre());
        }
        if((historiaPersonal.getPrimerApellido() == null) && (result.getPrimerApellido() != null)) {
            historiaPersonal.setPrimerApellido(result.getPrimerApellido());
        }
        if((historiaPersonal.getCedula() == null) && (result.getCedula() != null)) {
            historiaPersonal.setCedula(result.getCedula());
        }
        if((historiaPersonal.getEmail() == null) && (result.getEmail() != null)) {
            historiaPersonal.setEmail(result.getEmail());
        }
        historiaPersonalAdultoRepository.save(historiaPersonal);

        return ResponseEntity.created(new URI("/api/historia-medica-adultos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /historia-medica-adultos : Updates an existing historiaMedicaAdulto.
     *
     * @param historiaMedicaAdulto the historiaMedicaAdulto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated historiaMedicaAdulto,
     * or with status 400 (Bad Request) if the historiaMedicaAdulto is not valid,
     * or with status 500 (Internal Server Error) if the historiaMedicaAdulto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/historia-medica-adultos")
    public ResponseEntity<HistoriaMedicaAdulto> updateHistoriaMedicaAdulto(@Valid @RequestBody HistoriaMedicaAdulto historiaMedicaAdulto) throws URISyntaxException {
        log.debug("REST request to update HistoriaMedicaAdulto : {}", historiaMedicaAdulto);
        if (historiaMedicaAdulto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoriaMedicaAdulto result = historiaMedicaAdultoRepository.save(historiaMedicaAdulto);

        HistoriaPersonalAdulto historiaPersonal = historiaPersonalAdultoRepository.getByPaciente_Id(result.getPaciente().getId()).get(0);
        if((historiaPersonal.getPrimerNombre() == null) && (result.getPrimerNombre() != null)) {
            historiaPersonal.setPrimerNombre(result.getPrimerNombre());
        }
        if((historiaPersonal.getPrimerApellido() == null) && (result.getPrimerApellido() != null)) {
            historiaPersonal.setPrimerApellido(result.getPrimerApellido());
        }
        if((historiaPersonal.getCedula() == null) && (result.getCedula() != null)) {
            historiaPersonal.setCedula(result.getCedula());
        }
        if((historiaPersonal.getEmail() == null) && (result.getEmail() != null)) {
            historiaPersonal.setEmail(result.getEmail());
        }
        historiaPersonalAdultoRepository.save(historiaPersonal);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, historiaMedicaAdulto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /historia-medica-adultos : get all the historiaMedicaAdultos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of historiaMedicaAdultos in body
     */
    @GetMapping("/historia-medica-adultos")
    public ResponseEntity<List<HistoriaMedicaAdulto>> getAllHistoriaMedicaAdultos(Pageable pageable) {
        log.debug("REST request to get a page of HistoriaMedicaAdultos");
        Page<HistoriaMedicaAdulto> page = historiaMedicaAdultoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/historia-medica-adultos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/historia-medica-adultos/{id}")
    public ResponseEntity<HistoriaMedicaAdulto> getHistoriaMedicaAdulto(@PathVariable Long id) {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<HistoriaMedicaAdulto> historiaMedicaAdulto = historiaMedicaAdultoRepository.findById(id);

        List<EvolucionPaciente> evos = evolucionPacienteRepository.findByCita_Paciente_Id(historiaMedicaAdulto.get().getPaciente().getId());
        historiaMedicaAdulto.get().setEvolucionId(evos.get(0).getId());

        return ResponseUtil.wrapOrNotFound(historiaMedicaAdulto);
    }

    /**
     * GET  /historia-medica-adultos/:id : get the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the historiaMedicaAdulto, or with status 404 (Not Found)
     */
    @GetMapping("/historia-medica-adultos/file/{id}")
    public ResponseEntity<HistoriaMedicaAdulto> getHistoriaMedicaAdultoFile(@PathVariable Long id) throws IOException, DocumentException, IllegalAccessException {
        log.debug("REST request to get HistoriaMedicaAdulto : {}", id);
        Optional<HistoriaMedicaAdulto> historia = historiaMedicaAdultoRepository.findById(id);

        String template = plantillaHistoriaMedicaAdultoRepository.findAll().get(0).getContenido();

        User paciente = historia.get().getPaciente();

        HistoriaPersonalAdulto historiaPersonal = historiaPersonalAdultoRepository.getByPaciente_Id(paciente.getId()).get(0);

        template = template.replace("@fechaActual", getFecha(LocalDate.now()));

        if(paciente.getNumeroHistoria() != null)
            template = template.replace("@pacienteNumeroHistoria", paciente.getNumeroHistoria().toString());
        else
            template = template.replace("@pacienteNumeroHistoria", " - ");

        if(historiaPersonal.getSexo() != null)
            template = template.replace("@sexo", historiaPersonal.getSexo().getNombre());
        else
            template = template.replace("@sexo", " - ");

        if(historiaPersonal.getFechaNacimiento() != null)
            template = template.replace("@fechaNacimiento", getFecha(historiaPersonal.getFechaNacimiento()));
        else
            template = template.replace("@fechaNacimiento", " - ");

        if(historia.get().getEdadAbuelos() != null)
            template = template.replace("@edadAbuelos", historia.get().getEdadAbuelos());
        else
            template = template.replace("@edadAbuelos", " - ");

        if(historia.get().getEdadTios() != null)
            template = template.replace("@edadTios", historia.get().getEdadTios());
        else
            template = template.replace("@edadTios", " - ");

        if(historia.get().getEdadPadre() != null)
            template = template.replace("@edadPadre", historia.get().getEdadPadre());
        else
            template = template.replace("@edadPadre", " - ");

        if(historia.get().getEdadMadre() != null)
            template = template.replace("@edadMadre", historia.get().getEdadMadre());
        else
            template = template.replace("@edadMadre", " - ");

        if(historia.get().getEdadHermanos() != null)
            template = template.replace("@edadHermanos", historia.get().getEdadHermanos());
        else
            template = template.replace("@edadHermanos", " - ");

        if(historiaPersonal.getFechaNacimiento() != null)
            template = template.replace("@edad", getAge(historiaPersonal.getFechaNacimiento()));
        else
            template = template.replace("@edad", " - ");

        Boolean llenadosMedicamentos = false;
        if (historia.get().getMedicamento1() != null) {
            Gson gson = new Gson();

            Medicamento[] medicamentoArray = gson.fromJson(historia.get().getMedicamento1(), Medicamento[].class);

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
                template = template.replace("@medicamento1",medicamentos);
            } else {
                template = template.replace("@medicamento1", "No se encuentra bajo hábitos medicamentosos.");
            }

        } else {
            template = template.replace("@medicamento1", "No se encuentra bajo hábitos medicamentosos.");
        }

        String file = getPdf(historia.get(), template);
        historia.get().setArchivo(file);

        return ResponseUtil.wrapOrNotFound(historia);
    }

    public String getPdf(HistoriaMedicaAdulto historia, String template) throws IOException, DocumentException, IllegalAccessException {

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

    /**
     * DELETE  /historia-medica-adultos/:id : delete the "id" historiaMedicaAdulto.
     *
     * @param id the id of the historiaMedicaAdulto to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/historia-medica-adultos/{id}")
    public ResponseEntity<Void> deleteHistoriaMedicaAdulto(@PathVariable Long id) {
        log.debug("REST request to delete HistoriaMedicaAdulto : {}", id);
        historiaMedicaAdultoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
