import { Component, OnInit, Injector, KeyValueChanges, KeyValueDiffer, KeyValueDiffers, DoCheck } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';
import { HistoriaPersonalNinoService } from './historia-personal-nino.service';
import { ISexo } from 'app/shared/model/sexo.model';
import { SexoService } from 'app/entities/sexo';
import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';
import { ReferenciaMedicaService } from 'app/entities/referencia-medica';
import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from 'app/entities/estado-civil';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { TipoConsultaService } from 'app/entities/tipo-consulta';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad';
import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';
import { GradoInstruccionService } from 'app/entities/grado-instruccion';
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from 'app/entities/cita';

import { RxSpeechRecognitionService, resultList } from '@kamiazya/ngx-speech-recognition';

@Component({
    selector: 'jhi-historia-personal-nino-update',
    templateUrl: './historia-personal-nino-update.component.html'
})
export class HistoriaPersonalNinoUpdateComponent implements OnInit, DoCheck {
    historiaPersonalNino: IHistoriaPersonalNino;
    isSaving: boolean;

    historiaDiffer: KeyValueDiffer<string, any>;
    didChange: boolean;

    sexos: ISexo[];

    referenciamedicas: IReferenciaMedica[];

    estadocivils: IEstadoCivil[];

    tipoconsultas: ITipoConsulta[];

    especialidads: IEspecialidad[];

    gradoinstruccions: IGradoInstruccion[];

    citas: ICita[];
    fechaNacimientoDp: any;

    service: any;

    listen(iten, field) {
        console.log(iten[field]);
        let auxIten = (iten[field] ? iten[field] : '') + ' ';
        this.service
            .listen()
            .pipe(resultList)
            .subscribe((list: SpeechRecognitionResultList) => {
                iten[field] = auxIten + list.item(0).item(0).transcript;
                console.log('RxComponent:onresult', iten, list);
            });
    }

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected historiaPersonalNinoService: HistoriaPersonalNinoService,
        protected sexoService: SexoService,
        protected referenciaMedicaService: ReferenciaMedicaService,
        protected estadoCivilService: EstadoCivilService,
        protected tipoConsultaService: TipoConsultaService,
        protected especialidadService: EspecialidadService,
        protected gradoInstruccionService: GradoInstruccionService,
        protected citaService: CitaService,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        private injector: Injector,
        private differs: KeyValueDiffers
    ) {
        let isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
        if (isChrome) {
            this.service = injector.get(RxSpeechRecognitionService);
        }
    }

    ngOnInit() {
        this.isSaving = false;
        this.didChange = false;
        this.activatedRoute.data.subscribe(({ historiaPersonalNino }) => {
            this.historiaPersonalNino = historiaPersonalNino;
            this.historiaDiffer = this.differs.find(this.historiaPersonalNino).create();
            this.calculate_age(this.historiaPersonalNino.fechaNacimiento);
            this.didChange = false;
        });
        this.sexoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISexo[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISexo[]>) => response.body)
            )
            .subscribe((res: ISexo[]) => (this.sexos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.referenciaMedicaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IReferenciaMedica[]>) => mayBeOk.ok),
                map((response: HttpResponse<IReferenciaMedica[]>) => response.body)
            )
            .subscribe((res: IReferenciaMedica[]) => (this.referenciamedicas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.estadoCivilService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEstadoCivil[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEstadoCivil[]>) => response.body)
            )
            .subscribe((res: IEstadoCivil[]) => (this.estadocivils = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoConsultaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITipoConsulta[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITipoConsulta[]>) => response.body)
            )
            .subscribe((res: ITipoConsulta[]) => (this.tipoconsultas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.especialidadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidad[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidad[]>) => response.body)
            )
            .subscribe((res: IEspecialidad[]) => (this.especialidads = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.gradoInstruccionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IGradoInstruccion[]>) => mayBeOk.ok),
                map((response: HttpResponse<IGradoInstruccion[]>) => response.body)
            )
            .subscribe((res: IGradoInstruccion[]) => (this.gradoinstruccions = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.citaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICita[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICita[]>) => response.body)
            )
            .subscribe((res: ICita[]) => (this.citas = res), (res: HttpErrorResponse) => this.onError(res.message));

        var _this = this;
        setTimeout(function() {
            _this.didChange = false;
        }, 1000);
    }

    previousState() {
        if (window.location.href.includes('#'))
            window.location.replace('/#/historia-medica-nino/' + this.historiaPersonalNino.historiaMedicaId + '/edit');
        else window.location.replace('?/historia-medica-nino/' + this.historiaPersonalNino.historiaMedicaId + '/edit');
    }

    save() {
        this.isSaving = true;
        if (this.historiaPersonalNino.id !== undefined) {
            this.subscribeToSaveResponse(this.historiaPersonalNinoService.update(this.historiaPersonalNino));
        } else {
            this.subscribeToSaveResponse(this.historiaPersonalNinoService.create(this.historiaPersonalNino));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoriaPersonalNino>>) {
        result.subscribe(
            (res: HttpResponse<IHistoriaPersonalNino>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
        window.scrollTo(0, 0);
        this.jhiAlertService.success('innapcitasApp.historiaPersonalNino.exito', null, null);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSexoById(index: number, item: ISexo) {
        return item.id;
    }

    trackReferenciaMedicaById(index: number, item: IReferenciaMedica) {
        return item.id;
    }

    trackEstadoCivilById(index: number, item: IEstadoCivil) {
        return item.id;
    }

    trackTipoConsultaById(index: number, item: ITipoConsulta) {
        return item.id;
    }

    trackEspecialidadById(index: number, item: IEspecialidad) {
        return item.id;
    }

    trackGradoInstruccionById(index: number, item: IGradoInstruccion) {
        return item.id;
    }

    trackCitaById(index: number, item: ICita) {
        return item.id;
    }

    getFile() {
        this.historiaPersonalNinoService
            .getFile(this.historiaPersonalNino.id)
            .pipe(
                filter((mayBeOk: HttpResponse<IHistoriaPersonalNino>) => mayBeOk.ok),
                map((response: HttpResponse<IHistoriaPersonalNino>) => response.body)
            )
            .subscribe(
                (res: IHistoriaPersonalNino) => this.dataUtils.openFile('application/pdf', res.archivo),
                (res: HttpErrorResponse) => this.onError('error')
            );
    }

    calculate_age(fecha) {
        var dob = new Date(fecha);
        var diff_ms = Date.now() - dob.getTime();
        var age_dt = new Date(diff_ms);
        console.log(age_dt);
        this.historiaPersonalNino.edad = Math.abs(age_dt.getUTCFullYear() - 1970);
    }

    ngDoCheck() {
        const changes = this.historiaDiffer.diff(this.historiaPersonalNino);
        console.log(changes);
        if (changes) {
            this.didChange = true;
        }
    }

    next() {
        if (this.didChange) {
            if (confirm('¿Desea guardar los cambios?')) {
                this.save();
            } else {
                this.previousState();
                window.scrollTo(0, 0);
            }
        } else {
            this.previousState();
            window.scrollTo(0, 0);
        }
    }
}
