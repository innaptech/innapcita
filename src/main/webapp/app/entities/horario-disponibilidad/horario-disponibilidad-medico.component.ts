import { Component, OnInit, OnDestroy, ChangeDetectionStrategy, ViewChild, TemplateRef } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Subject } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { HorarioDisponibilidadService } from './horario-disponibilidad.service';

import { CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView } from 'angular-calendar';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { startOfDay, endOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours, addMinutes } from 'date-fns';

const colors: any = {
    red: {
        primary: '#ad2121',
        secondary: '#FAE3E3'
    },
    blue: {
        primary: '#1e90ff',
        secondary: '#D1E8FF'
    },
    yellow: {
        primary: '#e3bc08',
        secondary: '#FDF1BA'
    }
};

@Component({
    selector: 'jhi-horario-disponibilidad',
    templateUrl: './horario-disponibilidad-medico.component.html'
})
export class HorarioDisponibilidadMedicoComponent implements OnInit, OnDestroy {
    currentAccount: any;
    horarioDisponibilidads: IHorarioDisponibilidad[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    horarioLunes: any;
    horarioMartes: any;
    horarioMiercoles: any;
    horarioJueves: any;
    horarioViernes: any;
    horarioSabado: any;
    horarioDomingo: any;
    lunesName: any;
    martesName: any;
    miercolesName: any;
    juevesName: any;
    viernesName: any;
    sabadoName: any;
    domingoName: any;

    modalContent: TemplateRef<any>;

    view: CalendarView = CalendarView.Month;

    CalendarView = CalendarView;

    viewDate: Date = new Date();

    modalData: {
        action: string;
        event: CalendarEvent;
    };

    handleEvent(action: string, event: CalendarEvent): void {
        this.modalData = { event, action };
        this.modal.open(this.modalContent, { size: 'lg' });
    }

    actions: CalendarEventAction[] = [
        {
            label: '<i class="fa fa-fw fa-pencil"></i>',
            onClick: ({ event }: { event: CalendarEvent }): void => {
                this.handleEvent('Edited', event);
            }
        },
        {
            label: '<i class="fa fa-fw fa-times"></i>',
            onClick: ({ event }: { event: CalendarEvent }): void => {
                this.events = this.events.filter(iEvent => iEvent !== event);
                this.handleEvent('Deleted', event);
            }
        }
    ];

    events: CalendarEvent[] = [];

    activeDayIsOpen = true;

    constructor(
        protected horarioDisponibilidadService: HorarioDisponibilidadService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private modal: NgbModal
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.horarioDisponibilidadService
                .search({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IHorarioDisponibilidad[]>) => this.paginateHorarioDisponibilidads(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.horarioDisponibilidadService
            .medico({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IHorarioDisponibilidad[]>) => this.paginateHorarioDisponibilidads(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/horario-disponibilidad/medico'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate([
            '/horario-disponibilidad/medico',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate([
            '/horario-disponibilidad/medico',
            {
                search: this.currentSearch,
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInHorarioDisponibilidads();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IHorarioDisponibilidad) {
        return item.id;
    }

    registerChangeInHorarioDisponibilidads() {
        this.eventSubscriber = this.eventManager.subscribe('horarioDisponibilidadListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    getMon(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff));
    }
    getTue(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 1));
    }
    getWed(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 2));
    }
    getThu(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 3));
    }
    getFri(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 4));
    }
    getSat(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 5));
    }
    getSun(d) {
        d = new Date(d);
        var day = d.getDay(),
            diff = d.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(d.setDate(diff + 6));
    }

    protected paginateHorarioDisponibilidads(data: IHorarioDisponibilidad[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.horarioDisponibilidads = data;

        this.horarioLunes = [];
        this.horarioMartes = [];
        this.horarioMiercoles = [];
        this.horarioJueves = [];
        this.horarioViernes = [];
        this.horarioSabado = [];
        this.horarioDomingo = [];

        console.log(data);

        for (let i = 0; i < this.horarioDisponibilidads.length; i++) {
            const horario = this.horarioDisponibilidads[i];
            if (horario.diaSemana.nombre.toUpperCase() === 'LUNES' || horario.diaSemana.nombre.toUpperCase() === 'MONDAY') {
                this.lunesName = horario.diaSemana.nombre;
                this.horarioLunes.push(horario);
                for (var p = 0; p < horario.horas.length; p++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getMon(new Date())), horario.horas[p].horaInicio),
                                horario.horas[p].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getMon(new Date())), horario.horas[p].horaFin),
                                horario.horas[p].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'MARTES' || horario.diaSemana.nombre.toUpperCase() === 'TUESDAY') {
                this.martesName = horario.diaSemana.nombre;
                this.horarioMartes.push(horario);
                for (var j = 0; j < horario.horas.length; j++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getTue(new Date())), horario.horas[j].horaInicio),
                                horario.horas[j].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getTue(new Date())), horario.horas[j].horaFin),
                                horario.horas[j].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'MIÉRCOLES' || horario.diaSemana.nombre.toUpperCase() === 'WEDNESDAY') {
                this.miercolesName = horario.diaSemana.nombre;
                this.horarioMiercoles.push(horario);
                for (var k = 0; k < horario.horas.length; k++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getWed(new Date())), horario.horas[k].horaInicio),
                                horario.horas[k].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getWed(new Date())), horario.horas[k].horaFin),
                                horario.horas[k].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'JUEVES' || horario.diaSemana.nombre.toUpperCase() === 'THURSDAY') {
                this.juevesName = horario.diaSemana.nombre;
                this.horarioJueves.push(horario);
                for (var l = 0; l < horario.horas.length; l++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getThu(new Date())), horario.horas[l].horaInicio),
                                horario.horas[l].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getThu(new Date())), horario.horas[l].horaFin),
                                horario.horas[l].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'VIERNES' || horario.diaSemana.nombre.toUpperCase() === 'FRIDAY') {
                this.viernesName = horario.diaSemana.nombre;
                this.horarioViernes.push(horario);
                for (var m = 0; m < horario.horas.length; m++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getFri(new Date())), horario.horas[m].horaInicio),
                                horario.horas[m].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getFri(new Date())), horario.horas[m].horaFin),
                                horario.horas[m].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'SÁBADO' || horario.diaSemana.nombre.toUpperCase() === 'SATURDAY') {
                this.sabadoName = horario.diaSemana.nombre;
                this.horarioSabado.push(horario);
                for (var n = 0; n < horario.horas.length; n++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getSat(new Date())), horario.horas[n].horaInicio),
                                horario.horas[n].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getSat(new Date())), horario.horas[n].horaFin),
                                horario.horas[n].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            } else if (horario.diaSemana.nombre.toUpperCase() === 'DOMINGO' || horario.diaSemana.nombre.toUpperCase() === 'SUNDAY') {
                this.domingoName = horario.diaSemana.nombre;
                this.horarioDomingo.push(horario);
                for (var o = 0; o < horario.horas.length; o++) {
                    this.events = [
                        ...this.events,
                        {
                            title: horario.especialidadMedico.especialidad.especialidad,
                            start: addMinutes(
                                addHours(startOfDay(this.getSun(new Date())), horario.horas[o].horaInicio),
                                horario.horas[o].minutoInicio
                            ),
                            end: addMinutes(
                                addHours(startOfDay(this.getSun(new Date())), horario.horas[o].horaFin),
                                horario.horas[o].minutoFin
                            ),
                            color: colors.blue,
                            draggable: true,
                            resizable: {
                                beforeStart: true,
                                afterEnd: true
                            }
                        }
                    ];
                }
            }
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    addEvent(): void {
        this.events = [
            ...this.events,
            {
                title: 'New event',
                start: startOfDay(new Date()),
                end: endOfDay(new Date()),
                color: colors.blue,
                draggable: true,
                resizable: {
                    beforeStart: true,
                    afterEnd: true
                }
            }
        ];
    }

    deleteEvent(eventToDelete: CalendarEvent) {
        this.events = this.events.filter(event => event !== eventToDelete);
    }

    setView(view: CalendarView) {
        this.view = view;
    }

    closeOpenMonthViewDay() {
        this.activeDayIsOpen = false;
    }
}
