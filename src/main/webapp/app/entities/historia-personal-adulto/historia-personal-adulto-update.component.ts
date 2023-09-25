import { Component, OnInit, Injector, KeyValueChanges, KeyValueDiffer, KeyValueDiffers, DoCheck } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';
import { HistoriaPersonalAdultoService } from './historia-personal-adulto.service';
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
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from 'app/entities/cita';

import { RxSpeechRecognitionService, resultList } from '@kamiazya/ngx-speech-recognition';

@Component({
    selector: 'jhi-historia-personal-adulto-update',
    templateUrl: './historia-personal-adulto-update.component.html'
})
export class HistoriaPersonalAdultoUpdateComponent implements OnInit, DoCheck {
    historiaPersonalAdulto: IHistoriaPersonalAdulto;

    historiaDiffer: KeyValueDiffer<string, any>;
    didChange: boolean;

    isSaving: boolean;

    sexos: ISexo[];

    referenciamedicas: IReferenciaMedica[];

    estadocivils: IEstadoCivil[];

    tipoconsultas: ITipoConsulta[];

    especialidads: IEspecialidad[];

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
        protected historiaPersonalAdultoService: HistoriaPersonalAdultoService,
        protected sexoService: SexoService,
        protected referenciaMedicaService: ReferenciaMedicaService,
        protected estadoCivilService: EstadoCivilService,
        protected tipoConsultaService: TipoConsultaService,
        protected especialidadService: EspecialidadService,
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
        this.activatedRoute.data.subscribe(({ historiaPersonalAdulto }) => {
            this.historiaPersonalAdulto = historiaPersonalAdulto;
            this.calculate_age(this.historiaPersonalAdulto.fechaNacimiento);
            this.historiaDiffer = this.differs.find(this.historiaPersonalAdulto).create();
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
            window.location.replace('/#/historia-medica-adulto/' + this.historiaPersonalAdulto.historiaMedicaId + '/edit');
        else window.location.replace('?/historia-medica-adulto/' + this.historiaPersonalAdulto.historiaMedicaId + '/edit');
    }

    save() {
        this.isSaving = true;
        if (this.historiaPersonalAdulto.id !== undefined) {
            this.subscribeToSaveResponse(this.historiaPersonalAdultoService.update(this.historiaPersonalAdulto));
        } else {
            this.subscribeToSaveResponse(this.historiaPersonalAdultoService.create(this.historiaPersonalAdulto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoriaPersonalAdulto>>) {
        result.subscribe(
            (res: HttpResponse<IHistoriaPersonalAdulto>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.jhiAlertService.success('innapcitasApp.historiaPersonalAdulto.exito', null, null);
        window.scrollTo(0, 0);
        this.previousState();
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

    trackCitaById(index: number, item: ICita) {
        return item.id;
    }

    getFile() {
        this.historiaPersonalAdultoService
            .getFile(this.historiaPersonalAdulto.id)
            .pipe(
                filter((mayBeOk: HttpResponse<IHistoriaPersonalAdulto>) => mayBeOk.ok),
                map((response: HttpResponse<IHistoriaPersonalAdulto>) => response.body)
            )
            .subscribe(
                (res: IHistoriaPersonalAdulto) => this.dataUtils.openFile('application/pdf', res.archivo),
                (res: HttpErrorResponse) => this.onError('error')
            );
    }

    calculate_age(fecha) {
        var dob = new Date(fecha);
        var diff_ms = Date.now() - dob.getTime();
        var age_dt = new Date(diff_ms);
        console.log(age_dt);
        this.historiaPersonalAdulto.edad = Math.abs(age_dt.getUTCFullYear() - 1970);
    }

    ngDoCheck() {
        const changes = this.historiaDiffer.diff(this.historiaPersonalAdulto);
        console.log(changes);
        if (changes) {
            this.didChange = true;
        }
    }

    next() {
        console.log(this.didChange);
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
