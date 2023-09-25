import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IEstatusCita } from 'app/shared/model/estatus-cita.model';
import { EstatusCitaService } from 'app/entities/estatus-cita';

import { ICita } from 'app/shared/model/cita.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { CitaService } from './cita.service';

import { NgAddToCalendarService, ICalendarEvent } from '@trademe/ng-add-to-calendar';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';

@Component({
    selector: 'jhi-cita',
    templateUrl: './cita-user.component.html'
})
export class CitaUserComponent implements OnInit, OnDestroy {
    currentAccount: any;
    citas: ICita[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    criteria: any;
    zonaHoraria: string;

    estatusCitas: IEstatusCita[];

    advancedCriteria: string;
    criteriaEstatusCita: IEstatusCita;
    criteriaTipoCita: string;
    criteriaMesCita: string;
    criteriaFechaCita: string;
    criteriaFechaFinalCita: string;

    cardView: any;

    googleCalendarEventUrl: SafeUrl;
    newEvent: ICalendarEvent;

    constructor(
        protected citaService: CitaService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected estatusCitaService: EstatusCitaService,
        protected _addToCalendarService: NgAddToCalendarService,
        protected _sanitizer: DomSanitizer
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.criteria = '';
        this.cardView = true;
        this.advancedCriteria = undefined;
        this.criteriaEstatusCita = undefined;
        this.criteriaFechaCita = undefined;
        this.criteriaFechaFinalCita = undefined;
        this.criteriaMesCita = undefined;
        this.criteriaTipoCita = undefined;

        this.citaService
            .queryUser({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICita[]>) => this.paginateCitas(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.estatusCitaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEstatusCita[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEstatusCita[]>) => response.body)
            )
            .subscribe((res: IEstatusCita[]) => (this.estatusCitas = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    getCalendarLink(cita) {
        let googleCalendarEventUrl;

        if (cita.fecha) {
            let direccion = cita.consultaOnline
                ? 'Online'
                : 'AVENIDA FRANCISCO FAJARDO, EDIFICIO CENTRO SILU, PISO 1, Caracas 1011, Distrito Capital, Venezuela';

            let title = 'Cita INNAP ';
            if (cita.especialidadMedico && cita.especialidadMedico.medico && cita.especialidadMedico.medico) {
                title = title + cita.especialidadMedico.medico.firstName + ' ' + cita.especialidadMedico.medico.lastName;
            }
            this.newEvent = {
                // Event title
                title: title,
                // Event start date
                start: cita.fecha,
                // Event duration (IN MINUTES)
                duration: 30,
                end: cita.fecha,
                // Event Address (optional)
                address: direccion,
                // Event Description (optional)
                description: title
            };

            googleCalendarEventUrl = this._sanitizer.bypassSecurityTrustUrl(
                this._addToCalendarService.getHrefFor(this._addToCalendarService.calendarType.google, this.newEvent)
            );
        }

        return googleCalendarEventUrl;
    }

    search(query) {
        this.citaService
            .search(query ? query : this.criteria ? this.criteria : 'empty')
            .subscribe(
                (res: HttpResponse<ICita[]>) => this.paginateCitas(res.body, res.headers),
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
        this.router.navigate(['/cita/user'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'desc' : 'asc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/cita/user',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'desc' : 'asc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCitas();
        this.zonaHoraria = Intl.DateTimeFormat().resolvedOptions().timeZone;
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICita) {
        return item.id;
    }

    registerChangeInCitas() {
        this.eventSubscriber = this.eventManager.subscribe('citaListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'desc' : 'asc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateCitas(data: ICita[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.citas = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    save(cita) {
        this.subscribeToSaveResponse(this.citaService.update(cita));
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICita>>) {
        result.subscribe((res: HttpResponse<ICita>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {}

    protected onSaveError() {}

    updateEstatus(cita, finalizar) {
        if (finalizar) {
            console.log(finalizar);
            for (var i = 0; i < this.estatusCitas.length; i++) {
                if (this.estatusCitas[i].id == 12251) {
                    cita.estatusCita = this.estatusCitas[i];
                    console.log(cita);
                }
            }
        }
        this.save(cita);
    }

    advancedCriteriaUpdate() {
        this.advancedCriteria = this.criteria;

        if (this.criteriaEstatusCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaEstatusCita.nombre;
        }

        if (this.criteriaTipoCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaTipoCita;
        }

        if (this.criteriaMesCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaMesCita;
        }

        if (this.criteriaFechaCita && this.criteriaFechaFinalCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaFechaCita + '*' + this.criteriaFechaFinalCita;
        } else if (this.criteriaFechaCita && !this.criteriaFechaFinalCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaFechaCita;
        } else if (!this.criteriaFechaCita && this.criteriaFechaFinalCita) {
            this.advancedCriteria = this.advancedCriteria + '+' + this.criteriaFechaFinalCita;
        } else if (!this.criteriaFechaCita && !this.criteriaFechaFinalCita) {
            //nothing at all
        }

        console.log(this.advancedCriteria);

        this.search(this.advancedCriteria);
    }

    advancedCriteriaUpdateFecha() {
        this.criteriaMesCita = '';
        this.advancedCriteriaUpdate();
    }

    advancedCriteriaUpdateFechaFinal() {
        this.criteriaMesCita = '';
        this.advancedCriteriaUpdate();
    }

    advancedCriteriaUpdateMes() {
        this.criteriaFechaCita = '';
        this.advancedCriteriaUpdate();
    }

    changeView() {
        this.cardView = !this.cardView;
    }
}
