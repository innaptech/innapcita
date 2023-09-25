import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from './cita.service';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { EspecialidadMedicoService } from 'app/entities/especialidad-medico';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { TipoConsultaService } from 'app/entities/tipo-consulta';
import { AccountService } from 'app/core';
import { IUser, UserService } from 'app/core';

import { NgAddToCalendarService, ICalendarEvent } from '@trademe/ng-add-to-calendar';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';

@Component({
    selector: 'jhi-cita-update',
    templateUrl: './cita-update.component.html'
})
export class CitaUpdateComponent implements OnInit {
    cita: ICita;
    isSaving: boolean;

    nuevo: boolean;

    users: IUser[];

    especialidadmedicos: IEspecialidadMedico[];

    especialidads: IEspecialidad[];

    tipoconsultas: ITipoConsulta[];

    fecha: string;
    time: string;

    keyword = 'descripcionConcat';

    keyword2 = 'email';

    datePop: boolean;

    isAdmin: boolean;

    paciente: IUser;

    googleCalendarEventUrl: SafeUrl;
    newEvent: ICalendarEvent;

    showAddCalendar: boolean;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected citaService: CitaService,
        protected userService: UserService,
        protected especialidadMedicoService: EspecialidadMedicoService,
        protected especialidadService: EspecialidadService,
        protected tipoConsultaService: TipoConsultaService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected accountService: AccountService,
        protected _addToCalendarService: NgAddToCalendarService,
        protected _sanitizer: DomSanitizer
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cita }) => {
            this.cita = cita;
            this.fecha = this.cita.fecha != null ? this.cita.fecha.format(DATE_TIME_FORMAT) : null;
            if (!cita.id) {
                this.nuevo = true;
                this.accountService.identity().then(account => {
                    console.log(account);
                    var isAdmin = false;
                    for (var i = 0; i < account.authorities.length; i++) {
                        if (account.authorities[i] == 'ROLE_MEDICO' || account.authorities[i] == 'ROLE_ADMIN') {
                            isAdmin = true;
                        }
                    }
                    this.isAdmin = isAdmin;
                    console.log(isAdmin);
                    if (!isAdmin) {
                        this.cita.paciente = account;
                    }
                });
            }
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.especialidadMedicoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidadMedico[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidadMedico[]>) => response.body)
            )
            .subscribe(
                (res: IEspecialidadMedico[]) => {
                    for (let i = 0; i < res.length; i++) {
                        let esp = res[i];
                        console.log(esp);
                        esp.descripcionConcat =
                            esp.descripcion +
                            ' ' +
                            esp.medico.firstName +
                            ' ' +
                            esp.medico.lastName +
                            ' ' +
                            esp.medico.login +
                            ' ' +
                            esp.especialidad.especialidad;
                    }
                    this.especialidadmedicos = res;
                    this.activatedRoute.params.subscribe(params => {
                        let idMedico = params['medico'];
                        console.log(idMedico);
                        for (var i = 0; i < res.length; i++) {
                            let esp = res[i];
                            if (esp.medico.id == idMedico) {
                                this.cita.especialidadMedico = esp;
                                this.cita.especialidad = esp.especialidad;
                            }
                        }
                    });
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.especialidadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidad[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidad[]>) => response.body)
            )
            .subscribe((res: IEspecialidad[]) => (this.especialidads = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoConsultaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITipoConsulta[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITipoConsulta[]>) => response.body)
            )
            .subscribe((res: ITipoConsulta[]) => (this.tipoconsultas = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    createCalendar() {
        /*this.newEvent = {
            // Event title
            title: 'Cita INNAP',
            // Event start date
            start: moment(this.fecha + ' ' + this.time, DATE_TIME_FORMAT),
            // Event duration (IN MINUTES)
            duration: 30,
            end: moment(this.fecha + ' ' + this.time, DATE_TIME_FORMAT)
            // Event Address (optional)
            //address: '1 test street, testland',
            // Event Description (optional)
            //description: 'Cita INNAP'
        };

        this.googleCalendarEventUrl = this._sanitizer.bypassSecurityTrustUrl(
            this._addToCalendarService.getHrefFor(this._addToCalendarService.calendarType.google, this.newEvent)
        );

        this.showAddCalendar = true;*/
    }

    showDate() {
        this.datePop = !this.datePop;
    }
    setEspecialidad() {
        console.log(this.cita.especialidadMedico);
        if (this.cita.especialidadMedico) {
            this.cita.especialidad = this.cita.especialidadMedico.especialidad;
        }
    }

    selectEvent(item) {
        // do something with selected item
        console.log(item);
        if (item) {
            this.cita.especialidadMedico = item;
            this.cita.especialidad = this.cita.especialidadMedico.especialidad;
        }
    }

    selectEventPaciente(item) {
        // do something with selected item
        console.log(item);
        if (item) {
            this.cita.paciente = item;
        }
    }

    onChangeSearchUsers(val: string) {
        // fetch remote data from here
        // And reassign the 'data' which is binded to 'data' property.
        if (val && val != '') {
            this.userService
                .querySearch(val)
                .pipe(
                    filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                    map((response: HttpResponse<IUser[]>) => response.body)
                )
                .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        } else {
            this.userService
                .query()
                .pipe(
                    filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                    map((response: HttpResponse<IUser[]>) => response.body)
                )
                .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    onChangeSearchEspecialidades(val: string) {
        // fetch remote data from here
        // And reassign the 'data' which is binded to 'data' property.
        if (val && val != '') {
            this.especialidadMedicoService
                .querySearch(val)
                .pipe(
                    filter((mayBeOk: HttpResponse<IEspecialidadMedico[]>) => mayBeOk.ok),
                    map((response: HttpResponse<IEspecialidadMedico[]>) => response.body)
                )
                .subscribe(
                    (res: IEspecialidadMedico[]) => {
                        for (let i = 0; i < res.length; i++) {
                            let esp = res[i];
                            console.log(esp);
                            esp.descripcionConcat =
                                esp.descripcion +
                                ' ' +
                                esp.medico.firstName +
                                ' ' +
                                esp.medico.lastName +
                                ' ' +
                                esp.medico.login +
                                ' ' +
                                esp.especialidad.especialidad;
                        }
                        this.especialidadmedicos = res;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.especialidadMedicoService
                .query()
                .pipe(
                    filter((mayBeOk: HttpResponse<IEspecialidadMedico[]>) => mayBeOk.ok),
                    map((response: HttpResponse<IEspecialidadMedico[]>) => response.body)
                )
                .subscribe(
                    (res: IEspecialidadMedico[]) => {
                        for (let i = 0; i < res.length; i++) {
                            let esp = res[i];
                            console.log(esp);
                            esp.descripcionConcat =
                                esp.descripcion +
                                ' ' +
                                esp.medico.firstName +
                                ' ' +
                                esp.medico.lastName +
                                ' ' +
                                esp.medico.login +
                                ' ' +
                                esp.especialidad.especialidad;
                        }
                        this.especialidadmedicos = res;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    onFocused(e) {
        // do something when input is focused
    }

    save() {
        this.isSaving = true;
        this.cita.fecha = this.fecha != null ? moment(this.fecha + ' ' + this.time, DATE_TIME_FORMAT) : null;
        if (this.cita.id !== undefined) {
            this.subscribeToSaveResponse(this.citaService.update(this.cita));
        } else {
            this.subscribeToSaveResponse(this.citaService.create(this.cita));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICita>>) {
        result.subscribe((res: HttpResponse<ICita>) => this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess(cita) {
        console.log(cita);
        this.isSaving = false;
        window.scrollTo(0, 0);
        if (this.nuevo) {
            this.jhiAlertService.success('innapcitasApp.cita.citaexitosa', null, null);
            try {
                if (cita.pacienteNino) {
                    this.router.navigate(['historia-personal-adulto/' + cita.historiaPersonalNinos[0].id + '/edit']);
                } else {
                    this.router.navigate(['historia-personal-adulto/' + cita.historiaPersonalAdultos[0].id + '/edit']);
                }
            } catch (ex) {
                console.log(ex);
                this.router.navigate(['cita/user']);
            }
        } else {
            this.router.navigate(['cita/user']);
        }
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackEspecialidadMedicoById(index: number, item: IEspecialidadMedico) {
        return item.id;
    }

    trackEspecialidadById(index: number, item: IEspecialidad) {
        return item.id;
    }

    trackTipoConsultaById(index: number, item: ITipoConsulta) {
        return item.id;
    }

    dateChanged(event) {
        this.showDate();
    }
}
