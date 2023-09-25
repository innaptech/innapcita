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

@Component({
    selector: 'jhi-cita',
    templateUrl: './cita.component.html'
})
export class CitaComponent implements OnInit, OnDestroy {
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
    zonaHoraria: string;

    estatusCitas: IEstatusCita[];

    constructor(
        protected citaService: CitaService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected estatusCitaService: EstatusCitaService
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
        this.citaService
            .query({
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

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/cita'], {
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
            '/cita',
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

    protected onSaveSuccess() {
    }

    protected onSaveError() {
    }

    updateEstatus(cita) {
        this.save(cita);
    }
}
